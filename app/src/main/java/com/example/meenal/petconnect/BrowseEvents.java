package com.example.meenal.petconnect;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.EventLog;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class BrowseEvents extends AppCompatActivity {
    private DatabaseReference mDatabase;
    private ListView listView;
    private CustomEventAdapter adapter;
    private ArrayList<EventProfile> eventList;
    private Spinner sortSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("HELPME", "HALPHALPHALP");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browse_events);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarevent);
        setSupportActionBar(toolbar);
        EditText search = (EditText) findViewById(R.id.eventsearch);

        mDatabase = FirebaseDatabase.getInstance().getReference().child("Events");
        Query mEvents = mDatabase.orderByKey();

        eventList = new ArrayList<>();

        String[] sortOptions = {"Sort", "Soonest to Latest", "Latest to Soonest"};
        sortSpinner = (Spinner) findViewById(R.id.sortMenuEvent);
        sortSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Log.d("TEST", "Spinner selected" + i);
                switch (i) {
                    case 0:
                        break;
                    case 1: // Young to Old
                        Collections.sort(eventList, new Comparator<EventProfile>() {
                            @Override
                            public int compare(EventProfile p1, EventProfile p2) {
                                String[] p1Date = p1.getDate().split("/");
                                int p1m = Integer.parseInt(p1Date[0]),
                                        p1d = Integer.parseInt(p1Date[1]),
                                        p1y = Integer.parseInt(p1Date[2]);
                                String[] p1Time = p1.getTime().split(":");
                                int p1h = Integer.parseInt(p1Time[0]),
                                        p1min = Integer.parseInt(p1Time[1]);
                                String[] p2Date = p2.getDate().split("/");
                                int p2m = Integer.parseInt(p2Date[0]),
                                        p2d = Integer.parseInt(p2Date[1]),
                                        p2y = Integer.parseInt(p2Date[2]);
                                String[] p2Time = p2.getTime().split(":");
                                int p2h = Integer.parseInt(p2Time[0]),
                                        p2min = Integer.parseInt(p2Time[1]);

                                if (p2y > p1y) {
                                    return -1;
                                } else if (p2y < p1y) {
                                    return 1;
                                } else if (p2m > p1m) {
                                    return -1;
                                } else if (p2m < p1m) {
                                    return 1;
                                } else if (p2d > p1d) {
                                    return -1;
                                } else if (p2d < p1d) {
                                    return 1;
                                } else if (p2h < p1h) {
                                    return -1;
                                } else if (p2h > p1h) {
                                    return 1;
                                } else if (p2min < p1min) {
                                    return -1;
                                } else if (p2min > p1min) {
                                    return 1;
                                } else {
                                    return 0;
                                }
                            }
                        });
                        break;
                    case 2: // Old to Young
                        Collections.sort(eventList, new Comparator<EventProfile>() {
                            @Override
                            public int compare(EventProfile p1, EventProfile p2) {
                                Log.d("TEST", p1.getDate().split("/")[0]);
                                String[] p1Date = p1.getDate().split("/");
                                int p1m = Integer.parseInt(p1Date[0]),
                                        p1d = Integer.parseInt(p1Date[1]),
                                        p1y = Integer.parseInt(p1Date[2]);
                                String[] p1Time = p1.getTime().split(":");
                                int p1h = Integer.parseInt(p1Time[0]),
                                        p1min = Integer.parseInt(p1Time[1]);
                                String[] p2Date = p2.getDate().split("/");
                                int p2m = Integer.parseInt(p2Date[0]),
                                        p2d = Integer.parseInt(p2Date[1]),
                                        p2y = Integer.parseInt(p2Date[2]);
                                String[] p2Time = p2.getTime().split(":");
                                int p2h = Integer.parseInt(p2Time[0]),
                                        p2min = Integer.parseInt(p2Time[1]);

                                if (p2y > p1y) {
                                    return 1;
                                } else if (p2y < p1y) {
                                    return -1;
                                } else if (p2m > p1m) {
                                    return 1;
                                } else if (p2m < p1m) {
                                    return -1;
                                } else if (p2d > p1d) {
                                    return 1;
                                } else if (p2d < p1d) {
                                    return -1;
                                } else if (p2h < p1h) {
                                    return 1;
                                } else if (p2h > p1h) {
                                    return -1;
                                } else if (p2min < p1min) {
                                    return 1;
                                } else if (p2min > p1min) {
                                    return -1;
                                } else {
                                    return 0;
                                }
                            }
                        });
                        break;
                }


                adapter = new CustomEventAdapter(eventList, getApplicationContext());
                listView.setAdapter(adapter);

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ArrayAdapter sortAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, sortOptions);
        sortAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sortSpinner.setAdapter(sortAdapter);

        mEvents.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    final String t1 = (String) ((Map) snapshot.getValue()).get("Date");
                    final String t2 = (String) ((Map) snapshot.getValue()).get("Location");
                    final String t3 = (String) ((Map) snapshot.getValue()).get("Name");
                    final String t4 = (String) ((Map) snapshot.getValue()).get("Time");
                    final String t5 = (String) ((Map) snapshot.getValue()).get("Image");
                    if (t5 != null) {
                        FirebaseStorage storage = FirebaseStorage.getInstance();
                        StorageReference storageRef = storage.getReference();
                        StorageReference petRef = storageRef.child(t5);

                        final long ONE_MEGABYTE = 1024 * 1024;
                        petRef.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
                            @Override
                            public void onSuccess(byte[] bytes) {
                                Bitmap bmp = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                                BitmapDrawable bitmap = new BitmapDrawable(bmp);

                                EventProfile event = new EventProfile(t1, t2, t3, t4, bitmap);
                                eventList.add(event);


                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception exception) {
                                // Handle any errors
                            }
                        });
                    } else {
                        eventList.add(new EventProfile(t1, t2, t3, t4));
                    }

//                    EventProfile event = new EventProfile(t1,t2,t3,t4);
////                    Log.d("Hi again", event.toString());
////                    Log.d("Hi again", (String) ((Map) snapshot.getValue()).get("Date"));
//
//                    eventList.add(event);

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

//        Drawable sparky = getResources().getDrawable(R.drawable.sparky);
//        Drawable kitty = getResources().getDrawable(R.drawable.kitty);
//
//        eventList.add(new EventProfile("Adopt-a-cat!", sparky));
//        eventList.add(new EventProfile("Adopt-a-dog!", kitty));

        listView = (ListView) findViewById(R.id.listviewevent);
        final Context context = getApplicationContext();
        adapter = new CustomEventAdapter(eventList, getApplicationContext());
        listView.setAdapter(adapter);
        Log.d("HELPME", "HALP is not here");
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(BrowseEvents.this, EventActivity.class);
                i.putExtra("name", eventList.get(position).getName());
                i.putExtra("image", eventList.get(position).getBitmap());
                startActivity(i);
            }
        });

    }
}

