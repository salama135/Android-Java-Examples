package com.example.android_java_examples;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import com.example.android_java_examples.helper.LocationListener;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

public class MapsMainActivity extends AppCompatActivity implements OnMapReadyCallback {

    private static final int MY_PERMISSIONS_REQUEST_READ_FINE_LOCATION = 100;
    GoogleMap googleMap;
    LocationListener locationListener;
    LocationManager locationManager;
    Button buttonShowCurrentLocation;
    EditText editTextLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps_main);

        editTextLocation = (EditText) findViewById(R.id.editTextLoaction);
        buttonShowCurrentLocation = (Button) findViewById(R.id.buttonShowCurrentLocation);

        locationListener = new LocationListener(getApplicationContext());
        locationManager = (LocationManager) getApplicationContext().getSystemService(Context.LOCATION_SERVICE);


        if (ContextCompat.checkSelfPermission(MapsMainActivity.this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                    this, // Activity
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    MY_PERMISSIONS_REQUEST_READ_FINE_LOCATION);
        }

        try {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
                    6000, 0, locationListener);
        } catch (SecurityException ignored) {
            Log.e("maps", ignored.getMessage());
        }

        SupportMapFragment supportMapFragment = (SupportMapFragment)
                getSupportFragmentManager().findFragmentById(R.id.fragment);

        supportMapFragment.getMapAsync(this);


    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        this.googleMap = googleMap;
        this.googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(30.04441960, 31.235711600), 8));

        buttonShowCurrentLocation.setOnClickListener(v -> {
            this.googleMap.clear();
            Geocoder geocoder = new Geocoder(getApplicationContext());
            List<Address> addressList;
            Location location = null;

            try {
                location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            } catch (SecurityException ex) {
                Log.e("maps", "hello 2");
            }

            if (location != null) {
                Log.e("maps", "hello 4");

                LatLng myLatLng = new LatLng(location.getLatitude(), location.getLongitude());

                try {

                    addressList = geocoder.getFromLocation(myLatLng.latitude, myLatLng.longitude, 1);

                    if (!addressList.isEmpty()) {
                        Log.e("maps", "hello 6");

                        String address = "";

                        for (int i=0;i<=addressList.get(0).getMaxAddressLineIndex(); i++){
                            address += addressList.get(0).getAddressLine(i) + ", ";
                        }

                        this.googleMap.addMarker(
                                new MarkerOptions().
                                        position(myLatLng).
                                        title("My Location").
                                        snippet(address)).
                                setDraggable(true);

                        editTextLocation.setText(address);
                    }

                } catch (IOException e) {
                    Log.e("maps", "hello 5");
                    this.googleMap.addMarker(new MarkerOptions().position(myLatLng).title("My Location"));
                }

                this.googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(myLatLng, 15));
            } else {
                Log.e("maps", "hello 1");
            }
        });

        this.googleMap.setOnMarkerDragListener(new GoogleMap.OnMarkerDragListener() {
            @Override
            public void onMarkerDragStart(@NonNull Marker marker) {

            }

            @Override
            public void onMarkerDrag(@NonNull Marker marker) {

            }

            @Override
            public void onMarkerDragEnd(@NonNull Marker marker) {
                Geocoder geocoder = new Geocoder(getApplicationContext());
                List<Address> addressList;
                Log.e("maps", "hello 7");
                try {

                    addressList = geocoder.getFromLocation(marker.getPosition().latitude, marker.getPosition().longitude, 1);
                    Log.e("maps", "hello 8");
                    if (!addressList.isEmpty()) {
                        String address = "";

                        for (int i=0;i<=addressList.get(0).getMaxAddressLineIndex(); i++){
                            address += addressList.get(0).getAddressLine(i) + ", ";
                        }

                        editTextLocation.setText(address);
                    } else {
                        editTextLocation.getText().clear();
                    }

                } catch (IOException e) {

                }
            }
        });
    }
}