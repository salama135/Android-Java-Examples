package com.example.android_java_examples;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.example.android_java_examples.adapter.SimpleFragmentPagerAdapter;
import com.google.android.material.tabs.TabLayout;

public class Camera_SoundActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera__sound);

        ViewPager viewPager = findViewById(R.id.viewPager);
        viewPager.setAdapter(new SimpleFragmentPagerAdapter(getSupportFragmentManager()));

        TabLayout tabLayout = findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);
    }
}