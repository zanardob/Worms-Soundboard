package com.zanardo.wormssoundboard;

import android.app.Activity;
import android.content.Context;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Guilherme on 21/03/2016.
 */

public class MainActivity extends Activity {
    private SoundPool soundPool = null;
    private SoundAdapter mAdapter = null;
    private ArrayList<Sound> mSounds = null;
    private HashMap<Integer, Integer> mSoundPoolMap = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Creates the SoundPool and the map of Ids
        AudioAttributes attributes = new AudioAttributes.Builder().setUsage(AudioAttributes.USAGE_MEDIA).setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION).build();
        soundPool = new SoundPool.Builder().setAudioAttributes(attributes).build();
        mSoundPoolMap = new HashMap<>();

        // Adds all the sounds to the sound list
        mSounds = new ArrayList<>();
        addSound(mSounds, "attack");
        addSound(mSounds, "catch_this");
        addSound(mSounds, "coward");
        addSound(mSounds, "did_you_see");

        // Creates the adapter for the GridView and populates it
        mAdapter = new SoundAdapter(this, R.id.grid_item_button, mSounds);
        GridView gridView = (GridView) findViewById(R.id.gridview);
        gridView.setAdapter(mAdapter);
    }

    @Override
    public void onResume(){
        super.onResume();
        soundPool.autoResume();
    }

    @Override
    public void onPause(){
        super.onPause();
        soundPool.autoPause();
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        soundPool.release();
        soundPool = null;
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
        Sound sound = mSounds.get(v.getId());

        // Plays the sound based on the map that links Position <-> soundPoolId
        soundPool.play(mSoundPoolMap.get(sound.getSoundResourceId()), leftVolume, rightVolume, priority, loop, playbackRate);
    }

    private void addSound(ArrayList<Sound> sounds, String root){
        String btnText = getString(getResources().getIdentifier("button_" + root, "string", getPackageName()));
        int soundId = getResources().getIdentifier(root, "raw", getPackageName());
        int iconId = getResources().getIdentifier(root, "drawable", getPackageName());

        mSoundPoolMap.put(soundId, soundPool.load(this, soundId, 1));
        sounds.add(new Sound(btnText, soundId, iconId));
    }
}
