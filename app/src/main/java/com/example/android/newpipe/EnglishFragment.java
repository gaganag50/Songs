package com.example.android.newpipe;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;


public class EnglishFragment extends android.support.v4.app.Fragment {


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        int id = Color.parseColor("#999900");
        try {
        }catch (Exception e){
            Log.v("MainActivity" , "MainActivity");
        }

        View rootView = inflater.inflate(R.layout.main_word, container, false);


        View someView = rootView.findViewById(R.id.listview);// get Any child View

        View root = someView.getRootView();

//        android.R.color.holo_blue_dark
        root.setBackgroundColor(getResources().getColor(R.color.english));




        ArrayList<Singer> singers = new ArrayList<Singer>();
        singers.add(new Singer("charlie", R.drawable.charlie));


        singers.add(new Singer("green_day", R.drawable.green_day));
        singers.add(new Singer("justin", R.drawable.justin));
        singers.add(new Singer("shakira", R.drawable.shakira));
        singers.add(new Singer("ed_sheeran", R.drawable.ed_sheeran));


        SingerAdapter singerAdapter = new SingerAdapter(getActivity(), singers );
        final ListView listView = rootView.findViewById(R.id.listview);
        listView.setAdapter(singerAdapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Singer singerClicked = (Singer) listView.getItemAtPosition(position);

                String string = singerClicked.getSinger();


                if (string == "charlie") {

                    Intent intent = new Intent(getContext(), Charlie.class);
                    startActivity(intent);

                } else if (string == "green_day") {
                    Intent intent = new Intent(getContext(), GreenDay.class);
                    startActivity(intent);

                } else if (string == "justin") {
                    Intent intent = new Intent(getContext(), Justin.class);
                    startActivity(intent);

                } else if (string == "ed_sheeran") {
                    Intent intent = new Intent(getContext() , Ed_Sheeran.class);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(getContext(), Shakira.class);
                    startActivity(intent);
                }

            }


        });

        return rootView;
    }
}
