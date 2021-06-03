package com.example.android_java_examples.helper;

import android.content.Context;
import android.location.Location;

import androidx.annotation.NonNull;


public class LocationListener implements android.location.LocationListener {

    private Context context;

    public LocationListener(Context context) {
        this.context = context;
    }

    @Override
    public void onLocationChanged(@NonNull Location location) {

    }

    @Override
    public void onProviderDisabled(@NonNull String provider) {

    }

    @Override
    public void onProviderEnabled(@NonNull String provider) {

    }
}
