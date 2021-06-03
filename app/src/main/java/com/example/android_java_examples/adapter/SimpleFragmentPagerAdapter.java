package com.example.android_java_examples.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.android_java_examples.fragment.CameraFragment;
import com.example.android_java_examples.fragment.MusicFragment;
import com.example.android_java_examples.fragment.VoiceFragment;

public class SimpleFragmentPagerAdapter extends FragmentPagerAdapter {
    public SimpleFragmentPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new MusicFragment();
            case 1:
                return new CameraFragment();
            case 2:
                return new VoiceFragment();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return "Music Player";
            case 1:
                return "Camera";
            case 2:
                return "Voice";
        }
        return super.getPageTitle(position);
    }
}
