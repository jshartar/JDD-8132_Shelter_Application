package com.example.meenal.petconnect;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    //Drawable kitty = getResources().getDrawable(R.drawable.kitty);
    //PetProfile pet = new PetProfile("Kitty Cat the Great", kitty);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final Model model = Model.getInstance();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        TextView username;
        username = findViewById(R.id.status);
        //username.setText(UserAccount.getUserAccount());

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        Button button= (Button) findViewById(R.id.signout);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, GoogleSignInActivity.class));
            }
        });

        Button userDetail = (Button) findViewById(R.id.userdetails);
        userDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user = model.getCurrentUser();
                if (user != null) {
                    startActivity(new Intent(MainActivity.this, UserDetails.class));
                } else {
                    startActivity(new Intent(MainActivity.this, Registration.class));
                }
            }
        });

        Button browseEvents= (Button) findViewById(R.id.browseEvents);
        browseEvents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, BrowseEvents.class));
            }
        });

        Button browsePets= (Button) findViewById(R.id.bp);
        browsePets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, BrowsePets.class));
            }
        });
        Drawable kitty = getResources().getDrawable(R.drawable.kitty);
        ImageView image = (ImageView) findViewById(R.id.pet_image);
        image.setImageDrawable(kitty);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
//    @Override
//    public int describeContents() {
//        return 0;
//    }
//
//    @Override
//    public void writeToParcel(Parcel dest, int flags) {
//        dest.writeString(name);
//        Bitmap bitmap = (Bitmap) ((BitmapDrawable) image).getBitmap();
//        dest.writeParcelable(bitmap, flags);
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_gallery) {
            startActivity(new Intent(MainActivity.this, BrowsePets.class));

        } else if (id == R.id.nav_event) {
            startActivity(new Intent(MainActivity.this, BrowseEvents.class));
        } else if (id == R.id.nav_addPet) {
            startActivity(new Intent(MainActivity.this, RegisterPet.class));
        } else if (id == R.id.nav_addEvent) {
            startActivity(new Intent(MainActivity.this, AddEvent.class));
        } else if (id == R.id.nav_applyAdopt) {
            startActivity(new Intent(MainActivity.this, ApplyAdoption.class));
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
