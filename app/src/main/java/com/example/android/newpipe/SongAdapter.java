package com.example.android.newpipe;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class SongAdapter extends ArrayAdapter<Song> {




    public SongAdapter(Activity view, ArrayList<Song> songs) {
        super(view, 0, songs);

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;

        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_for_songs, parent, false);
        }
        final Song song = getItem(position);
        final TextView textView = listItemView.findViewById(R.id.song_textview);
        textView.setText(song.getSong());
        final ImageView imageView = listItemView.findViewById(R.id.play_btn);


        return listItemView;
    }

    //private void releaseAudioStorage()
}
