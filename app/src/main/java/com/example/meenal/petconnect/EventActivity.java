package com.example.meenal.petconnect;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import android.widget.ImageView;

import android.widget.TextView;

public class EventActivity extends AppCompatActivity {

    private EventProfile event;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


//        Bundle bundle = getIntent().getExtras();
//        if (bundle != null) {
//            this.pet = bundle.getParcelable("pet");
//        }
        Bitmap image = (Bitmap) getIntent().getParcelableExtra("image");
        Drawable pic = new BitmapDrawable(getResources(), image);

        ImageView profile_image = (ImageView) findViewById(R.id.event_profile_image);
        profile_image.setImageDrawable(pic);

        String name = getIntent().getStringExtra("name");
        TextView name_text = (TextView) findViewById(R.id.event_profile_name);
        name_text.setText(name);

    }

}
