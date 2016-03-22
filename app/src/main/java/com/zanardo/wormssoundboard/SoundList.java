package com.zanardo.wormssoundboard;

import android.content.Context;
import android.content.res.Resources;

import java.util.ArrayList;

/**
 * Created by Guilherme on 22/03/2016.
 */

public class SoundList extends ArrayList<Sound> {
    Context mContext;

    public SoundList(Context c){
        mContext = c;
    }

    // Finds all the resources of type "R.*.soundRoot" and wraps them into a Sound object to add on the list
    public boolean add(String soundRoot){
        String packageName = mContext.getPackageName();
        Resources resources = mContext.getResources();

        String btnText = mContext.getString(resources.getIdentifier("button_" + soundRoot, "string", packageName));
        int soundId = resources.getIdentifier(soundRoot, "raw", packageName);
        int iconId = resources.getIdentifier(soundRoot, "drawable", packageName);

        Sound sound = new Sound(btnText, soundId, iconId);
        return super.add(sound);
    }
}
