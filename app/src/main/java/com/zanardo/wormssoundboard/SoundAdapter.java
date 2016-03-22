package com.zanardo.wormssoundboard;

import android.content.Context;
import android.media.MediaPlayer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by Guilherme on 21/03/2016.
 */

public class SoundAdapter extends ArrayAdapter<Sound> {
    private Context context = null;
    private ArrayList<Sound> items;

    public SoundAdapter(Context context, int buttonResourceId, ArrayList<Sound> items){
        super(context, buttonResourceId, items);
        this.context = context;
        this.items = items;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        View gridView = convertView;

        if(gridView == null){
            LayoutInflater viewInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            gridView = viewInflater.inflate(R.layout.grid_item, null);
        }

        Sound sound = items.get(position);

        if(sound != null){
            Button btnSound = (Button) gridView.findViewById(R.id.grid_item_button);

            if(btnSound != null) {
                btnSound.setId(position);
                btnSound.setText(sound.getText());
                btnSound.setCompoundDrawablesWithIntrinsicBounds(sound.getIconResourceId(), 0, 0, 0);
            }
        }

        return gridView;
    }
}
