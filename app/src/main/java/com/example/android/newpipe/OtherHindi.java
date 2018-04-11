package com.example.android.newpipe;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;

import android.widget.ListView;

import java.util.ArrayList;



public class OtherHindi extends AppCompatActivity {
    private AudioManager mAudioManager;
    private MediaPlayer mMediaPlayer;
    private AudioManager.OnAudioFocusChangeListener mOnAudioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {
            Log.v("lauda", "hello");
            if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT || focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
                mMediaPlayer.pause();
                //mMediaPlayer.seekTo(0);
            } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                mMediaPlayer.start();
            } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                releaseMediaPlayer();
            }
        }
    };

    MediaPlayer.OnCompletionListener mOnCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
            releaseMediaPlayer();
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_word);
        mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);



        final ArrayList<Song> songs_by_arjit = new ArrayList<>();
        songs_by_arjit.add(new Song("zinda-bhaag_milkha_bhaag", R.raw.zinda_bhaag_milkha_bhaag));
        songs_by_arjit.add(new Song("awari", R.raw.awari));
        songs_by_arjit.add(new Song("banjaara", R.raw.banjaara));
        songs_by_arjit.add(new Song("bol_do_na_zara", R.raw.bol_do_na_zara));
        songs_by_arjit.add(new Song("mein_rahoon_ya_na_rahoon", R.raw.mein_rahoon_ya_na_rahoon));
        songs_by_arjit.add(new Song("tum_mile", R.raw.tum_mile));
        ListView listView = findViewById(R.id.listview);
        SongAdapter adapter = new SongAdapter(this, songs_by_arjit);
        listView.setAdapter(adapter);



        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Song song = songs_by_arjit.get(position);
                releaseMediaPlayer();
                int result = mAudioManager.requestAudioFocus(mOnAudioFocusChangeListener , AudioManager.STREAM_MUSIC , AudioManager.AUDIOFOCUS_GAIN);
                if(result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED){
                    mMediaPlayer = MediaPlayer.create(OtherHindi.this , song.getAudioResourceId());
                    mMediaPlayer.start();
                    mMediaPlayer.setOnCompletionListener(mOnCompletionListener);
                }
            }
        });
    }


    @Override
    protected void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }


    private void releaseMediaPlayer(){
        if(mMediaPlayer!=null){
            mMediaPlayer.release();
            mMediaPlayer = null;
            mAudioManager.abandonAudioFocus(mOnAudioFocusChangeListener);
        }
    }
}
