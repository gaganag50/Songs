package com.example.android.newpipe;

import android.app.Fragment;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.view.View;

    class SimpleFragmentPagerAdapter extends FragmentPagerAdapter {


    private String[] songs = {"hindi" , "english"};
    public SimpleFragmentPagerAdapter(FragmentManager fm) {
        super(fm);

    }

    @Override
    public android.support.v4.app.Fragment getItem(int position) {
        if(position == 0){
            return new HindiFragment();
        }else
            return new EnglishFragment();
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return songs[position];
    }

}
