package com.example.meenal.petconnect;

import android.content.Intent;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Map;

public class BrowsePets extends AppCompatActivity {
    private ListView listView;
    private static CustomPetAdapter adapter;
    private ArrayList<PetProfile> petList;
    private DatabaseReference mDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browse_pets);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        EditText search = (EditText) findViewById(R.id.petSearch);

        mDatabase = FirebaseDatabase.getInstance().getReference().child("Dogs");
        Query mPets = mDatabase.orderByKey();

        listView = (ListView) findViewById(R.id.listview);
        petList = new ArrayList<>();


        mPets.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    String t1 = (String) ((Map) snapshot.getValue()).get("Name");
                    String t2 = (String) ((Map) snapshot.getValue()).get("Age");
                    String t3 = (String) ((Map) snapshot.getValue()).get("AboutMe");
                    //Drawable t4 = (Drawable) ((Map) snapshot.getValue()).get("Pic");
                    String t5 = (String) ((Map) snapshot.getValue()).get("NeuterSpay");
                    String t6 = (String) ((Map) snapshot.getValue()).get("Fee");
                    String t7 = (String) ((Map) snapshot.getValue()).get("ChildFriendly");
                    String t8 = (String) ((Map) snapshot.getValue()).get("Breed");
                    String t9 = (String) ((Map) snapshot.getValue()).get("Location");
                    String t10 = (String) ((Map) snapshot.getValue()).get("Pet");
                    String t11 = (String) ((Map) snapshot.getValue()).get("Size");

                    PetProfile pet = new PetProfile(t1,t2,t3,t5,t6,t7,t8,t9,t10,t11);
//                    Log.d("Hi again", event.toString());
//                    Log.d("Hi again", (String) ((Map) snapshot.getValue()).get("Date"));

                    petList.add(pet);

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
                (BrowsePets.this).adapter.getFilter().filter(s);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        final Context context = getApplicationContext();

        Drawable sparky = getResources().getDrawable(R.drawable.sparky);
        Drawable kitty = getResources().getDrawable(R.drawable.kitty);

        petList.add(new PetProfile("Sparky", sparky));
        petList.add(new PetProfile("Poseidon", kitty));

        adapter = new CustomPetAdapter(petList, getApplicationContext());
        listView.setAdapter(adapter);

    }



}

