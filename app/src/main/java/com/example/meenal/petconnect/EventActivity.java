package com.example.meenal.petconnect;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public class EventActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Event event = new Event()
        Bundle b = getIntent().getExtras();
        String name = b.getString("name");
        String details = b.getString("details");
        Event event = new Event(name, details);
        TextView nameView = findViewById(R.id.eventName);
        TextView detailsView = findViewById(R.id.eventDetailsText);
        nameView.setText(event.getName());
        detailsView.setText(event.getDetails());

    }

}
