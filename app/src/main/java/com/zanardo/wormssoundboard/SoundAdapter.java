package com.zanardo.wormssoundboard;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;

/**
 * Created by Guilherme on 21/03/2016.
 */

public class SoundAdapter extends ArrayAdapter<Sound> {
    private Context mContext = null;
    private SoundList mSoundList;

    public SoundAdapter(Context context, int buttonResourceId, SoundList soundList){
        super(context, buttonResourceId, soundList);
        mContext = context;
        mSoundList = soundList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        View gridView;
        LayoutInflater viewInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if(convertView == null){
            gridView = viewInflater.inflate(R.layout.grid_item, null);

            Sound sound = mSoundList.get(position);

            Button btnSound = (Button) gridView.findViewById(R.id.grid_item_button);
            btnSound.setId(position);
            btnSound.setText(sound.getText());
            btnSound.setCompoundDrawablesWithIntrinsicBounds(sound.getIconResourceId(), 0, 0, 0);

        }else{
            gridView = convertView;
        }

        return gridView;
    }

    @Override
    public int getViewTypeCount(){
        return getCount();
    }

    @Override
    public int getItemViewType(int position){
        return position;
    }
}
