package com.example.android.newpipe;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.ArrayList;

class SingerAdapter extends ArrayAdapter<Singer>{

    private int mResourceColor;

    public SingerAdapter(Activity view, ArrayList<Singer> singers , int colorResourceId){

        super(view , 0 , singers);
        mResourceColor = colorResourceId;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if(listItemView == null){
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item , parent , false);

        }
        Singer singer = getItem(position);
        ImageView imageView = (ImageView)listItemView.findViewById(R.id.singer_image);
        imageView.setImageResource(singer.getImageResourceId());
        TextView textView = (TextView)listItemView.findViewById(R.id.singer_textview);



        textView.setText(singer.getSinger());

        View view = listItemView.findViewById(R.id.text_container);
        int color = ContextCompat.getColor(getContext() , mResourceColor);
        view.setBackgroundColor(color);

        
        return listItemView;
    }
}
