package com.example.android.newpipe;

import android.support.v7.app.AppCompatActivity;

public class Song extends AppCompatActivity{
    private String mSong;
    private int mAudioResourceId;
    public Song(String song , int audioResourceId){
        mSong = song;
        mAudioResourceId = audioResourceId;
    }
    public String getSong(){
        return mSong;
    }
    public int getAudioResourceId(){
        return mAudioResourceId;
    }
}
