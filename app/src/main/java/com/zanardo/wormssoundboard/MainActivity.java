package com.zanardo.wormssoundboard;

import android.app.Activity;
import android.content.Context;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;

import java.util.HashMap;

/**
 * Created by Guilherme on 21/03/2016.
 */

public class MainActivity extends Activity {
    private SoundPool soundPool = null;
    private SoundList mSoundList = null;
    private HashMap<Sound, Integer> mSoundPoolMap = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Adds all the sounds to the sound list
        mSoundList = new SoundList(getApplicationContext());
        mSoundList.add("attack");
        mSoundList.add("catch_this");
        mSoundList.add("coward");
        mSoundList.add("did_you_see");
        mSoundList.add("dumb");
        mSoundList.add("fire");
        mSoundList.add("go_away");
        mSoundList.add("goodbye");
        mSoundList.add("grenade");
        mSoundList.add("hello");
        mSoundList.add("hide_yourselves");
        mSoundList.add("i_am_awesome");
        mSoundList.add("i_did_it");
        mSoundList.add("idiot");
        mSoundList.add("incredible");
        mSoundList.add("kamikaze");
        mSoundList.add("leave_me_alone");
        mSoundList.add("mayday");
        mSoundList.add("no_problems");
        mSoundList.add("quick");
        mSoundList.add("run");
        mSoundList.add("shocking");
        mSoundList.add("take_it");
        mSoundList.add("thank_god");
        mSoundList.add("the_first_of_many");
        mSoundList.add("there_it_goes");
        mSoundList.add("this_sucks");
        mSoundList.add("weapons");
        mSoundList.add("yes_sir");
        mSoundList.add("you_missed");
        
        // Creates the adapter for the GridView and populates it
        SoundAdapter mAdapter = new SoundAdapter(this, R.id.grid_item_button, mSoundList);
        GridView gridView = (GridView) findViewById(R.id.gridview);
        gridView.setAdapter(mAdapter);
    }

    @Override
    public void onResume(){
        super.onResume();

        AudioAttributes attributes = new AudioAttributes.Builder().setUsage(AudioAttributes.USAGE_MEDIA).setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION).build();
        soundPool = new SoundPool.Builder().setAudioAttributes(attributes).build();
        mSoundPoolMap = new HashMap<>();
        loadSounds();
    }

    @Override
    public void onPause(){
        super.onPause();

        soundPool.release();
        soundPool = null;

        mSoundPoolMap.clear();
        mSoundPoolMap = null;
    }

    public void playSound(View v) {
        AudioManager audioManager = (AudioManager)getSystemService(Context.AUDIO_SERVICE);
        float curVolume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        float maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        float leftVolume = curVolume/maxVolume;
        float rightVolume = curVolume/maxVolume;
        int priority = 1;
        int loop = 0;
        float playbackRate = 1f;

        // Grabs the sound based on the position of the button
        Sound sound = mSoundList.get(v.getId());

        // Plays the sound based on the map that links Sound <-> soundPoolId
        soundPool.play(mSoundPoolMap.get(sound), leftVolume, rightVolume, priority, loop, playbackRate);
    }

    // Load all the sounds into the SoundPool
    private void loadSounds(){
        for(Sound s : mSoundList){
            mSoundPoolMap.put(s, soundPool.load(this, s.getSoundResourceId(), 1));
        }
    }
}
