package com.zanardo.wormssoundboard;

import android.media.AudioAttributes;
import android.media.SoundPool;
import android.view.View;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private SoundPool soundPool;

    private int attackSoundId;
    private int catchThisSoundId;
    private int cowardSoundId;
    private int didYouSeeSoundId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AudioAttributes attributes = new AudioAttributes.Builder().setUsage(AudioAttributes.USAGE_MEDIA).setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION).build();
        soundPool = new SoundPool.Builder().setAudioAttributes(attributes).build();

        // Load the sounds into the SoundPool
        attackSoundId = soundPool.load(this, R.raw.attack, 1);
        catchThisSoundId = soundPool.load(this, R.raw.catch_this, 1);
        cowardSoundId = soundPool.load(this, R.raw.coward, 1);
        didYouSeeSoundId = soundPool.load(this, R.raw.did_you_see, 1);

        // Register the Click Listener for each button
        findViewById(R.id.button_attack).setOnClickListener(this);
        findViewById(R.id.button_catch_this).setOnClickListener(this);
        findViewById(R.id.button_coward).setOnClickListener(this);
        findViewById(R.id.button_did_you_see).setOnClickListener(this);
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

    // Releases the resources used by the SoundPool
    @Override
    public void onDestroy(){
        super.onDestroy();
        soundPool.release();
        soundPool = null;
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.button_attack:
                soundPool.play(attackSoundId, 1, 1, 0, 0, 1);
                break;
            case R.id.button_catch_this:
                soundPool.play(catchThisSoundId, 1, 1, 0, 0, 1);
                break;
            case R.id.button_coward:
                soundPool.play(cowardSoundId, 1, 1, 0, 0, 1);
                break;
            case R.id.button_did_you_see:
                soundPool.play(didYouSeeSoundId, 1, 1, 0, 0, 1);
                break;
        }

    }
}
