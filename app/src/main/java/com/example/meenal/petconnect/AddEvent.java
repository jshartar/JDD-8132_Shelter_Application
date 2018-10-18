package com.example.meenal.petconnect;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class AddEvent extends AppCompatActivity {

    private DatabaseReference database = FirebaseDatabase.getInstance().getReference("Events");

    private void writeNewUser(String name, String date, String time, String location) {
        //database.child("Events").push();
        //database.child("Events").child(name).push().setValue(name);
        //EventProfile event = new EventProfile(name, location, time, date);

        HashMap<String, Object> eventdata = new HashMap<>();
        eventdata.put("Name", name);
        eventdata.put("Date", date);
        eventdata.put("Location", location);
        eventdata.put("Time", time);

        database.child(name).updateChildren(eventdata);

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button submit = (Button) findViewById(R.id.submitButton);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText n = (EditText) findViewById(R.id.eventNameInput);
                EditText d = (EditText) findViewById(R.id.dateInput);
                EditText t = (EditText) findViewById(R.id.timeInput);
                EditText l = (EditText) findViewById(R.id.locationInput);

                String name = n.getEditableText().toString();
                String date= d.getEditableText().toString();
                String time= t.getEditableText().toString();
                String location= l.getEditableText().toString();

                writeNewUser(name, date, time, location);

                Intent intent = new Intent(AddEvent.this, BrowseEvents.class);
                startActivity(intent);
            }
        });
    }

}
