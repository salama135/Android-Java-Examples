package com.example.currency_converter.service;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

import androidx.annotation.Nullable;

import com.example.currency_converter.R;

public class MusicService extends Service {
    private MediaPlayer player;

    @Override
    public void onCreate(){
        player = MediaPlayer.create(getApplicationContext(), R.raw.music);
        player.setLooping(false);
    }

    @Override
    public int onStartCommand(Intent i, int flags, int startId ){
        player.start();
        return super.onStartCommand(i, flags, startId);
    }

    @Override
    public void onDestroy(){
        player.stop();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}