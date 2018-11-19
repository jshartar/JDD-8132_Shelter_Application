package com.example.meenal.petconnect;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ShelterInfoActivity extends Activity {
    String[] info;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_shelter_info);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*0.9), (int)(height *0.5));

        final TextView shelterLocation = (TextView) findViewById(R.id.shelter_location);
        final TextView shelterAddress = (TextView) findViewById(R.id.shelter_address);
        final TextView shelterPhone = (TextView) findViewById(R.id.shelter_phone);

        String location = getIntent().getStringExtra("name");

        info = new String[3];
        info[0] = location;

//        DatabaseReference database = FirebaseDatabase.getInstance().getReference();
//        DatabaseReference petRef = database.child("Dogs").child(location);
//
//        petRef.child("Location").addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                String loc = dataSnapshot.getValue().toString();
//                setInfo(loc);
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });

        DatabaseReference db = FirebaseDatabase.getInstance().getReference();
        final DatabaseReference shelterRef = db.child("Shelter");
        shelterRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ShelterInfo sInfo = new ShelterInfo();
                sInfo.setLocation(dataSnapshot.child(info[0]).getValue(ShelterInfo.class).getLocation());
                sInfo.setAddress(dataSnapshot.child(info[0]).getValue(ShelterInfo.class).getAddress());
                sInfo.setPhone(dataSnapshot.child(info[0]).getValue(ShelterInfo.class).getPhone());

                setInfo(sInfo.getLocation());
                setInfoAdd(sInfo.getAddress());
                setInfoPhone(sInfo.getPhone());

                shelterLocation.setText(info[0]);
                shelterAddress.setText(info[1]);
                shelterPhone.setText(info[2]);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
//        shelterRef.child("Address").addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                String address = dataSnapshot.getValue().toString();
//                setInfoAdd(address);
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });
//
//        shelterRef.child("Phone").addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                String phone = dataSnapshot.getValue().toString();
//                setInfoPhone(phone);
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });


    }

    public void setInfo(String loc) {
        info[0] = loc;
    }

    public void setInfoAdd(String address) {
        info[1] = address;
    }

    public void setInfoPhone(String phone) {
        info[2] = phone;
    }
}
