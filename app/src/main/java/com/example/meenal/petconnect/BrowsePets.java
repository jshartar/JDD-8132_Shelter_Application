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
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

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

//        final Drawable sparky = getResources().getDrawable(R.drawable.sparky);
//        final Drawable kitty = getResources().getDrawable(R.drawable.kitty);
//        final PetProfile pet = new PetProfile("Arya",kitty);

        final DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Dogs");
        ref.orderByChild("Name").limitToFirst(25).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                final String name = dataSnapshot.getKey();

                DatabaseReference tempRef = ref.child(name);
                tempRef.child("Pic").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.getValue() != null) {
                            final String picID = dataSnapshot.getValue().toString();

                            FirebaseStorage storage = FirebaseStorage.getInstance();
                            StorageReference storageRef = storage.getReference();
                            StorageReference petRef = storageRef.child(picID);

                            final long ONE_MEGABYTE = 1024 * 1024;
                            petRef.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
                                @Override
                                public void onSuccess(byte[] bytes) {
                                    Bitmap bmp = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                                    BitmapDrawable bitmap = new BitmapDrawable(bmp);

                                    petList.add(new PetProfile(name, bitmap));


                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception exception) {
                                    // Handle any errors
                                }
                            });

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

//        Drawable sparky = getResources().getDrawable(R.drawable.sparky);
//        Drawable kitty = getResources().getDrawable(R.drawable.kitty);
//
//        petList.add(new PetProfile("Sparky", sparky));
//        petList.add(new PetProfile("Poseidon", kitty));

        adapter = new CustomPetAdapter(petList, getApplicationContext());
        listView.setAdapter(adapter);
    }

}

