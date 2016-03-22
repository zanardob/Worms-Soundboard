package com.zanardo.wormssoundboard;

import android.app.Activity;
import android.media.AudioAttributes;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import java.util.ArrayList;

/**
 * Created by Guilherme on 21/03/2016.
 */

public class MainActivity extends Activity {
    private ArrayList<Sound> mSounds = null;
    private SoundAdapter mAdapter = null;

    private SoundPool soundPool;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Adds all the sounds to the sound list
        mSounds = new ArrayList<>();
        addSound(mSounds, "attack");
        addSound(mSounds, "catch_this");
        addSound(mSounds, "coward");
        addSound(mSounds, "did_you_see");

        mAdapter = new SoundAdapter(this, R.id.grid_item_button, mSounds);

        GridView gridView = (GridView) findViewById(R.id.gridview);
        gridView.setAdapter(mAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.i("GridItem", "Button on position " + position + " was pressed!");

                Sound sound = mSounds.get(position);

                MediaPlayer mp = MediaPlayer.create(view.getContext(), sound.getSoundResourceId());
                mp.start();
            }
        });

        AudioAttributes attributes = new AudioAttributes.Builder().setUsage(AudioAttributes.USAGE_MEDIA).setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION).build();
        soundPool = new SoundPool.Builder().setAudioAttributes(attributes).build();
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
/**
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Log.i("GridItem", "Button on position " + position + " was pressed!");

        Sound sound = mSounds.get(position);

        MediaPlayer mp = MediaPlayer.create(this, sound.getSoundResourceId());
        mp.start();
    }
*/
    private void addSound(ArrayList<Sound> sounds, String root){
        String btnText = getString(getResources().getIdentifier("button_" + root, "string", getPackageName()));
        int soundId = getResources().getIdentifier(root, "raw", getPackageName());
        int iconId = getResources().getIdentifier(root, "drawable", getPackageName());

        sounds.add(new Sound(btnText, soundId, iconId));
    }
}
