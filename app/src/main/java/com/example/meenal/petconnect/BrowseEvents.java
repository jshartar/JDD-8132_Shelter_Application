package com.example.meenal.petconnect;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ListView;

import java.util.ArrayList;

public class BrowseEvents extends AppCompatActivity {
    private ListView listView;
    private static CustomPetAdapter adapter;
    private ArrayList<PetProfile> petList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browse_pets);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        listView = (ListView) findViewById(R.id.listview);
        petList = new ArrayList<>();

        petList.add(new PetProfile("Lucy", null));
        petList.add(new PetProfile("Molly", null));

        adapter = new CustomPetAdapter(petList, getApplicationContext());
        listView.setAdapter(adapter);
    }

}

