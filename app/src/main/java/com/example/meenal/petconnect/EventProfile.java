package com.example.meenal.petconnect;

import android.graphics.drawable.Drawable;

public class EventProfile {
    private String name;
    private Drawable image;

    public EventProfile(String name, Drawable image) {

        this.name = name;
        this.image = image;
    }

    public Drawable getImage() {
        return image;
    }

    public void setImage(Drawable image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

