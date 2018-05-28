package com.example.android.newpipe;

import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class HindiFragment extends android.support.v4.app.Fragment {

  public HindiFragment(){

  }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.main_word, container, false);
        ArrayList<Singer> singers = new ArrayList<Singer>();
        singers.add(new Singer("Arjit Singh", R.drawable.arjit_singh));
        singers.add(new Singer("Atif Aslam", R.drawable.atif_aslam));
        singers.add(new Singer("Honey Singh", R.drawable.honey_singh));
        singers.add(new Singer("Armaan Malik", R.drawable.armaan_malik));
        singers.add(new Singer("Other", R.drawable.others));


        SingerAdapter singerAdapter = new SingerAdapter(getActivity(), singers);
        final ListView listView = (ListView) rootView.findViewById(R.id.listview);
        listView.setAdapter(singerAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Singer singer = (Singer) listView.getItemAtPosition(position);
                String s = singer.getSinger();
                if (s == "Arjit Singh") {
                    Intent intent = new Intent(getContext(), ArjitSingh.class);
                    startActivity(intent);
                } else if (s == "Atif Aslam") {
                    Intent intent = new Intent(getContext(), AtifAslam.class);
                    startActivity(intent);

                } else if (s == "Honey Singh") {
                    Intent intent = new Intent(getContext(), HoneySingh.class);
                    startActivity(intent);

                } else if (s == "Armaan Malik") {
                    Intent intent = new Intent(getContext(), ArmaanMalik.class);
                    startActivity(intent);

                } else {
                    Intent intent = new Intent(getContext(), OtherHindi.class);
                    startActivity(intent);

                }

            }
        });

        return rootView;

    }
}
