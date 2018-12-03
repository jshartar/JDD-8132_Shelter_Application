/**
 *  Browse Pets
 */

// --- Dependencies --- //

package com.example.meenal.petconnect;

import android.content.Intent;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.ChildEventListener;
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
import java.util.Map;
// ---

/**
 *  Browse Pets Class and Avtivity
 */
public class BrowsePets extends AppCompatActivity {

    // --- Global Variables --- ///
    private ListView listView;
    private static CustomPetAdapter adapter;
    private ArrayList<PetProfile> petList;
    private Spinner sortSpinner;
    // ---


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browse_pets);

        // - Pull Objects from the Screen to Load Pet Profiles - //
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        EditText search = (EditText) findViewById(R.id.petsearch);
        listView = (ListView) findViewById(R.id.listview);
        petList = new ArrayList<>();
        String[] sortOptions = {"Sort", "Youngest to Oldest", "Oldest to Youngest"};
        sortSpinner = (Spinner) findViewById(R.id.sortMenu);
        // ---

        /**
         *  This Listener will contstantly check for changes, from the user, for sorting methods
         */
        sortSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Log.d("TEST", "Spinner selected" + i);
                switch (i) {
                    case 0:
                        break;
                    case 1: // Young to Old
                        Collections.sort(petList, new Comparator<PetProfile>() {
                            @Override
                            public int compare(PetProfile p1, PetProfile p2) {
                                return (int) (p1.getAge() - p2.getAge());
                            }
                        });
                        break;
                    case 2: // Old to Young
                        Collections.sort(petList, new Comparator<PetProfile>() {
                            @Override
                            public int compare(PetProfile p1, PetProfile p2) {
                                return (int) (p2.getAge() - p1.getAge());
                            }
                        });
                        break;
                }

                adapter = new CustomPetAdapter(petList, getApplicationContext());
                listView.setAdapter(adapter);

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        // - Set Spinner Adapter - //
        ArrayAdapter sortAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, sortOptions);
        sortAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sortSpinner.setAdapter(sortAdapter);
        // ---

        // --- Get Firebase, backend, reference to pull data from --- //
        final DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Dogs");

        /**
         *  This Listener will load 25 pet profile in alphabetical order anytime the database is changed
         */
        ref.orderByChild("Name").limitToFirst(25).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                final String name = dataSnapshot.getKey();

                DatabaseReference tempRef = ref.child(name);

                Query mPets = tempRef.orderByKey();
                mPets.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        final long age = (Long) ((Map) snapshot.getValue()).get("Age");
                        final String t5 = (String) ((Map) snapshot.getValue()).get("Pic");
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

                                    PetProfile pet = new PetProfile(name, age, bitmap);
                                    petList.add(pet);


                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception exception) {
                                    // Handle any errors
                                }
                            });
                        } else {
                            petList.add(new PetProfile(name, age));
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

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

        adapter = new CustomPetAdapter(petList, getApplicationContext());
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(BrowsePets.this, PetProfile_Activity.class);
                i.putExtra("name", petList.get(position).getName());
                i.putExtra("image", petList.get(position).getBitmap());
                startActivity(i);
            }
        });
    }

}

