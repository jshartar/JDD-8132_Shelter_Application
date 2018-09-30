package com.example.meenal.petconnect;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ListView;

import java.util.ArrayList;

public class BrowseEvents extends AppCompatActivity {
    private ListView listView;
    private static CustomEventAdapter adapter;
    private ArrayList<EventProfile> eventList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browse_events);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarevent);
        setSupportActionBar(toolbar);

        listView = (ListView) findViewById(R.id.listviewevent);
        eventList = new ArrayList<>();
        final Context context = getApplicationContext();

        Drawable sparky = getResources().getDrawable(R.drawable.sparky);
        Drawable kitty = getResources().getDrawable(R.drawable.kitty);

        eventList.add(new EventProfile("Adopt-a-cat!", sparky));
        eventList.add(new EventProfile("Adopt-a-dog!", kitty));

        adapter = new CustomEventAdapter(eventList, getApplicationContext());
        listView.setAdapter(adapter);
    }
}

