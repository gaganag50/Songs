package com.example.android.newpipe;

public  class Singer {

    private  String mSinger;
    private int mImageResourceId;
    public Singer( String singer  , int imageResourceId){

        mSinger = singer;
        mImageResourceId = imageResourceId;
    }

    public String getSinger(){
        return mSinger;
    }

    public int getImageResourceId(){
        return mImageResourceId;
    }
}
