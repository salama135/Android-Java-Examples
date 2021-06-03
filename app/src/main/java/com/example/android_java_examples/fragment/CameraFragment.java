package com.example.android_java_examples.fragment;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.example.android_java_examples.R;


public class CameraFragment extends Fragment {
    private static final int TAKE_PHOTO = 100;
    private ImageView myImg;
    private Uri imageUri;

    public CameraFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_camera, container, false);
        myImg = v.findViewById(R.id.imageView_iwanttodie);
        Button buttonOpenCam = v.findViewById(R.id.btn_opencamera);
        buttonOpenCam.setOnClickListener(v1 -> {
            
            int permissionCheck = ContextCompat.checkSelfPermission(getContext(), Manifest.permission.CAMERA);
            if (permissionCheck == PackageManager.PERMISSION_GRANTED) {

                // we have camera permission, open System camera
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                
                intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                startActivityForResult(intent, TAKE_PHOTO);

            } else {

                // we don't have it, request camera permission from system
                ActivityCompat.requestPermissions((Activity) getContext(),
                        new String[]{Manifest.permission.CAMERA},
                        TAKE_PHOTO);
            }


//            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//            startActivityForResult(intent, pic_id);
        });
        return v;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == TAKE_PHOTO && resultCode == Activity.RESULT_OK){
            Bitmap myimage = (Bitmap) data.getExtras().get("data");
            myImg.setImageBitmap(myimage);
            myImg.setVisibility(View.VISIBLE);
        }
    }
}
