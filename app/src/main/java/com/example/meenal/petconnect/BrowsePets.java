package com.example.meenal.petconnect;

import android.content.Intent;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class BrowsePets extends AppCompatActivity {
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
        final Context context = getApplicationContext();

        Drawable sparky = getResources().getDrawable(R.drawable.sparky);
        Drawable kitty = getResources().getDrawable(R.drawable.kitty);

        petList.add(new PetProfile("Sparky", sparky));
        petList.add(new PetProfile("Poseidon", kitty));

        adapter = new CustomPetAdapter(petList, getApplicationContext());
        listView.setAdapter(adapter);
    }

}

