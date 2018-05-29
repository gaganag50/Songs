package com.example.android.newpipe;


import android.annotation.TargetApi;
import android.content.Context;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;

import android.widget.ListView;

import java.util.ArrayList;

@TargetApi(Build.VERSION_CODES.O)
@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)


public class ArjitSingh extends AppCompatActivity {
//
//    private MediaPlayer mMediaPlayer;
//    private AudioManager mAudioManager;
//
//    AudioManager.OnAudioFocusChangeListener mOnAudioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {
//        @Override
//        public void onAudioFocusChange(int i) {
//            if(i == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT || i == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK){
//                mMediaPlayer.pause();
//                mMediaPlayer.seekTo(0);
//            }else if (i == AudioManager.AUDIOFOCUS_GAIN){
//                mMediaPlayer.start();
//            }else if (i == AudioManager.AUDIOFOCUS_LOSS){
//                releaseMediaPlayer();
//            }
//        }
//    };
//
//
//    private MediaPlayer.OnCompletionListener mCompletionListener = new MediaPlayer.OnCompletionListener() {
//        @Override
//        public void onCompletion(MediaPlayer mediaPlayer) {
//            releaseMediaPlayer();
//        }
//    };


    private MediaPlayer mMediaPlayer;
    private AudioManager mAudioManager;
    private AudioManager.OnAudioFocusChangeListener mOnAudioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {
            if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT || focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
                mMediaPlayer.pause();
                // mMediaPlayer.seekTo(0);
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
            Log.v("ahatatahtahtauhthathath", "antouht");
            releaseMediaPlayer();

        }
    };


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        Log.v("Hollollollo", "Arjit");


        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_word);

        mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);


        final ArrayList<Song> songs_by_arjit = new ArrayList<>();
        songs_by_arjit.add(new Song("dilliwaali_girlfrient", R.raw.arjit_singh_dilliwaali_girlfriend));
        songs_by_arjit.add(new Song("enna_sona", R.raw.arjit_singh_enna_sona));
        songs_by_arjit.add(new Song("hamari_adhuri_khanni", R.raw.arjit_singh_hamari_adhuri_kahani));
        songs_by_arjit.add(new Song("itni_si_baat_hai", R.raw.arjit_singh_itni_si_baat_hain));
        songs_by_arjit.add(new Song("lo_maan_liya", R.raw.arjit_singh_lo_maan_liya));
        songs_by_arjit.add(new Song("muskurane", R.raw.arjit_singh_muskurane));
        songs_by_arjit.add(new Song("raabta_kehte_hain", R.raw.arjit_singh_raabta_kehte_hain_khuda));
        songs_by_arjit.add(new Song("raaz_aankhein_teri", R.raw.arjit_singh_raaz_aankhein_teri));


        final ListView listView = findViewById(R.id.listview);
        SongAdapter adapter = new SongAdapter(ArjitSingh.this, songs_by_arjit);
        listView.setAdapter(adapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                Song song = songs_by_arjit.get(position);

                releaseMediaPlayer();

                int result = mAudioManager.requestAudioFocus(mOnAudioFocusChangeListener, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN);
                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {


                    Log.v("ArjitSingh", "AUDIOFOCUS_REQUEST_GRANTED");
                    mMediaPlayer = MediaPlayer.create(ArjitSingh.this, song.getAudioResourceId());
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

    private void releaseMediaPlayer() {
        if (mMediaPlayer != null) {
            mMediaPlayer.release();
            mMediaPlayer = null;

            mAudioManager.abandonAudioFocus(mOnAudioFocusChangeListener);
        }
    }
}
