package com.example.meenal.petconnect;

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
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

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

import java.io.File;
import java.util.ArrayList;

public class PetProfile_Activity extends AppCompatActivity {
    private PetProfile pet;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pet_profile);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        Bundle bundle = getIntent().getExtras();
//        if (bundle != null) {
//            this.pet = bundle.getParcelable("pet");
//        }
        Bitmap image = (Bitmap) getIntent().getParcelableExtra("image");
        Drawable pic = new BitmapDrawable(getResources(), image);

        final ImageView profile_image = (ImageView) findViewById(R.id.pet_profile_image);
//        profile_image.setImageDrawable(pic);

        String name = getIntent().getStringExtra("name");
        final TextView name_text = (TextView) findViewById(R.id.pet_profile_name);
        name_text.setText(name);

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Dogs");
        DatabaseReference tempRef = ref.child(name);
        tempRef.child("Pic").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                final String picID = dataSnapshot.getValue().toString();

                FirebaseStorage storage = FirebaseStorage.getInstance();
                StorageReference storageRef = storage.getReference();
                StorageReference petRef = storageRef.child(picID);

                final long ONE_MEGABYTE = 1024 * 1024;
                petRef.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
                    @Override
                    public void onSuccess(byte[] bytes) {
                        // Data for "images/island.jpg" is returns, use this as needed
                        Bitmap bmp = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
//                Bitmap mutableBitmap = bmp.copy(Bitmap.Config.ARGB_8888, true);
                        BitmapDrawable bitmap = new BitmapDrawable(bmp);

                        profile_image.setImageDrawable(bitmap);
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        name_text.setText(exception.toString());
                        // Handle any errors
                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        // --------- Load Profile Media --------- //

//        FirebaseStorage storage = FirebaseStorage.getInstance();
//        StorageReference storageRef = storage.getReference();
//        StorageReference petRef = storageRef.child("Arya_01.PNG");
//
//        final long ONE_MEGABYTE = 1024 * 1024;
//        petRef.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
//            @Override
//            public void onSuccess(byte[] bytes) {
//                // Data for "images/island.jpg" is returns, use this as needed
//                Bitmap bmp = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
////                Bitmap mutableBitmap = bmp.copy(Bitmap.Config.ARGB_8888, true);
//                BitmapDrawable bitmap = new BitmapDrawable(bmp);
//
//                profile_image.setImageDrawable(bitmap);
//            }
//        }).addOnFailureListener(new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull Exception exception) {
//                name_text.setText(exception.toString());
//                // Handle any errors
//            }
//        });

        // ------ Load Profile Features ------ //

        final TextView aboutme_text = (TextView) findViewById(R.id.pet_profile_aboutme_deets);
        final TextView age_text = (TextView) findViewById(R.id.pet_profile_age);
        final TextView breed_text = (TextView) findViewById(R.id.pet_profile_breed);
        final TextView size_text = (TextView) findViewById(R.id.pet_profile_size);
        final TextView fixed_text = (TextView) findViewById(R.id.pet_profile_fixed);
        final TextView fee_text = (TextView) findViewById(R.id.pet_profile_fee);
        final TextView location_text = (TextView) findViewById(R.id.pet_profile_location);

        DatabaseReference database = FirebaseDatabase.getInstance().getReference();
        DatabaseReference petRef = database.child("Dogs").child(name);

        petRef.child("Name").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String aboutme = dataSnapshot.getValue().toString();
                name_text.setText(aboutme);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        tempRef.child("AboutMe").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.getValue() != null) {
                    String aboutme = dataSnapshot.getValue().toString();
                    aboutme_text.setText(aboutme);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        tempRef.child("Age").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String aboutme = dataSnapshot.getValue().toString();
                age_text.setText(aboutme);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        tempRef.child("Breed").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String aboutme = dataSnapshot.getValue().toString();
                breed_text.setText(aboutme);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        tempRef.child("Size").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String aboutme = dataSnapshot.getValue().toString();
                size_text.setText(aboutme);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        tempRef.child("NeuterSpay").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String aboutme = dataSnapshot.getValue().toString();
                fixed_text.setText(aboutme);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        tempRef.child("Fee").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String aboutme = dataSnapshot.getValue().toString();
                fee_text.setText(aboutme);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        tempRef.child("Location").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String aboutme = dataSnapshot.getValue().toString();
                location_text.setText(aboutme);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        // ------ End of Data Loading ------ //

    }
}
