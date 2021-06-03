package com.example.android_java_examples.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.android_java_examples.R;
import com.example.android_java_examples.service.MusicService;


/**
 * A simple {@link Fragment} subclass.
 */
public class MusicFragment extends Fragment {
    Button playMusic;
    Button stopMusic;
    public MusicFragment() {
        // Required empty public constructor
        super(R.layout.fragment_music);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_music, container, false);
        playMusic = v.findViewById(R.id.btn_playmusic);
        stopMusic = v.findViewById(R.id.btn_stopmusic);

        playMusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity().getApplicationContext(), MusicService.class);
                getActivity().startService(i);
                Toast.makeText(getContext(),"Music Service Started", Toast.LENGTH_LONG).show();
            }
        });

        stopMusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity().getApplicationContext(), MusicService.class);
                getActivity().stopService(i);
                Toast.makeText(getContext(),"Music Service Stopped", Toast.LENGTH_LONG).show();
            }
        });
        return v;
    }
}
