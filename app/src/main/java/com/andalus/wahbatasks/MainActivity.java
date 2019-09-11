package com.andalus.wahbatasks;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Parcelable;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements SharedPreferences.OnSharedPreferenceChangeListener{

    private static final String TAG =MainActivity.class.getName();

    Button next, previous, play;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        next=findViewById(R.id.next);
        previous=findViewById(R.id.previous);
        play=findViewById(R.id.pause);


        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TaskIntentService.class);
                intent.setAction(PlayAudio.NEXT);
                startService(intent);

            }
        });

        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TaskIntentService.class);
                intent.setAction(PlayAudio.PREVIOUS);
                startService(intent);
            }
        });
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TaskIntentService.class);
                intent.setAction(PlayAudio.PLAY);
                startService(intent);
            }
        });


        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        prefs.registerOnSharedPreferenceChangeListener(this);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        prefs.unregisterOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key)
    {
//        if(key.equals(PlayAudio.PLAY))
//        {
//            PlayAudio.specifyStatus(this, key);
//        }
//        if(key.equals(PlayAudio.NEXT))
//        {
//            PlayAudio.specifyStatus(this, key);
//        }
//        if(key.equals(PlayAudio.PAUSE))
//        {
//            PlayAudio.specifyStatus(this, key);
//        }
//        if(key.equals(PlayAudio.PREVIOUS))
//        {
//            PlayAudio.specifyStatus(this, key);
//        }
        PlayAudio.specifyStatus(this, key);
    }
}
