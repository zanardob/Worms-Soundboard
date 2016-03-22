package com.zanardo.wormssoundboard;

/**
 * Created by Guilherme on 21/03/2016.
 */

public class Sound {
    private String mText = "";
    private int mSoundResourceId = -1;
    private int mIconResourceId = -1;

    public Sound(String text, int soundResourceId, int iconResourceId){
        mText = text;
        mSoundResourceId = soundResourceId;
        mIconResourceId = iconResourceId;
    }

    public String getText() {
        return mText;
    }

    public void setText(String text) {
        mText = text;
    }

    public int getSoundResourceId() {
        return mSoundResourceId;
    }

    public void setSoundResourceId(int soundResourceId) {
        mSoundResourceId = soundResourceId;
    }

    public int getIconResourceId() {
        return mIconResourceId;
    }

    public void setIconResourceId(int iconResourceId) {
        mIconResourceId = iconResourceId;
    }
}
