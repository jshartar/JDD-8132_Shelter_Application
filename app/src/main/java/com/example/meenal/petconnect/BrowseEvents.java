package com.example.meenal.petconnect;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.EventLog;
import android.util.Log;
import android.widget.EditText;
import android.widget.ListView;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class BrowseEvents extends AppCompatActivity {
    private DatabaseReference mDatabase;
    private ListView listView;
    private CustomEventAdapter adapter;
    private ArrayList<EventProfile> eventList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browse_events);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarevent);
        setSupportActionBar(toolbar);
        EditText search = (EditText) findViewById(R.id.eventsearch);

        mDatabase = FirebaseDatabase.getInstance().getReference().child("Events");
        Query mEvents = mDatabase.orderByKey();

        eventList = new ArrayList<>();

        mEvents.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    String t1 = (String) ((Map) snapshot.getValue()).get("Date");
                    String t2 = (String) ((Map) snapshot.getValue()).get("Location");
                    String t3 = (String) ((Map) snapshot.getValue()).get("Name");
                    String t4 = (String) ((Map) snapshot.getValue()).get("Time");

                    EventProfile event = new EventProfile(t1,t2,t3,t4);
//                    Log.d("Hi again", event.toString());
//                    Log.d("Hi again", (String) ((Map) snapshot.getValue()).get("Date"));

                    eventList.add(event);

                }
                listView.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                (BrowseEvents.this).adapter.getFilter().filter(s);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

//        Log.d("Hi again", eventList.toString());
//        Log.d("Hi again", "LAEHF;LASDKJF;ASLDKFJA;SLKDFJ");

        Drawable sparky = getResources().getDrawable(R.drawable.sparky);
        Drawable kitty = getResources().getDrawable(R.drawable.kitty);

        eventList.add(new EventProfile("Adopt-a-cat!", sparky));
        eventList.add(new EventProfile("Adopt-a-dog!", kitty));

        listView = (ListView) findViewById(R.id.listviewevent);
        final Context context = getApplicationContext();
        adapter = new CustomEventAdapter(eventList, getApplicationContext());
        listView.setAdapter(adapter);
//        Log.d("Hi again", "******* " + eventList.toString());

    }
}

