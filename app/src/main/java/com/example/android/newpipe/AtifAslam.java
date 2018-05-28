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
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public  class AtifAslam extends AppCompatActivity{
    private MediaPlayer mMediaPlayer;
    private AudioManager mAudioManage;

    private AudioManager.OnAudioFocusChangeListener mOnAudioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {
            Log.v("lauda", "hello");
            if(focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT || focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK){
                mMediaPlayer.pause();
                //mMediaPlayer.seekTo(0);
            }else if (focusChange == AudioManager.AUDIOFOCUS_GAIN){
                mMediaPlayer.start();
            }else if (focusChange == AudioManager.AUDIOFOCUS_LOSS){
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
        Log.v("Hollollollo" , "atoneuhshtaoenshu");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_word);

        mAudioManage = (AudioManager) getSystemService(Context.AUDIO_SERVICE);


        final ArrayList<Song> songs_by_arjit = new ArrayList<>();
        songs_by_arjit.add(new Song("bakhudia_tumhi_ho" , R.raw.atif_aslam_bakhuda_tumhi_ho));
        songs_by_arjit.add(new Song("dil_diyan_gallan" , R.raw.atif_aslam_dil_diyan_gallan));
        //songs_by_arjit.add(new Song("doorie" , R.raw.atif_aslam_doorie));
        songs_by_arjit.add(new Song("kuch_is_tarah" , R.raw.atif_aslam_kuch_is_tarah));
        songs_by_arjit.add(new Song("main_rang_sharbato" , R.raw.atif_aslam_main_rang_sharbaton_ka));
        songs_by_arjit.add(new Song("pehli_dafa" , R.raw.atif_aslam_pehli_dafa));
        songs_by_arjit.add(new Song("toota_jo_kabhi_tara" , R.raw.atif_aslam_toota_jo_kabhi_tara));
        songs_by_arjit.add(new Song("woh_lamhe_woh_baatein" , R.raw.atif_aslam_woh_lamhe));

        ListView listView = findViewById(R.id.listview);
        SongAdapter adapter = new SongAdapter( AtifAslam.this, songs_by_arjit);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Song song = songs_by_arjit.get(position);
                releaseMediaPlayer();
                int result = mAudioManage.requestAudioFocus(mOnAudioFocusChangeListener , AudioManager.STREAM_MUSIC , AudioManager.AUDIOFOCUS_GAIN);
                if(result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED){
                    Log.v("AtifAslam" , "AUDIOFOCUS_REQUEST_GRANTED");
                    mMediaPlayer = MediaPlayer.create(AtifAslam.this , song.getAudioResourceId());
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
            mAudioManage.abandonAudioFocus(mOnAudioFocusChangeListener);
        }
    }
}
