package com.example.meenal.petconnect;

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

        eventList.add(new EventProfile("Adoption Day", null));
        eventList.add(new EventProfile("Free Cats", null));

        adapter = new CustomEventAdapter(eventList, getApplicationContext());
        listView.setAdapter(adapter);
    }

}

