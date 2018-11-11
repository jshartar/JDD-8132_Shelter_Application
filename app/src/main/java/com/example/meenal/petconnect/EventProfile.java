package com.example.meenal.petconnect;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;

public class EventProfile implements Parcelable {
    private String name;
    private String location;
    private String time;
    private String description;
    private String date;
    private Drawable image;
    private Bitmap bitmap;

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public static final Parcelable.Creator<EventProfile> CREATOR = new Parcelable.Creator<EventProfile>() {
        public EventProfile createFromParcel(Parcel in) {
            return new EventProfile(in);
        }

        public EventProfile[] newArray(int size) {
            return new EventProfile[size];
        }
    };

    public EventProfile() {
        this.name = "";
        this.location = "";
        this.time = "";
        this.date = "";
        this.image = null;
        this.bitmap = null;
        this.description = "";
    }

    public EventProfile(String name, Drawable image, String description, String address, String time, String date) {
        this.name = name;
        this.description = description;
        this.location = address;
        this.time = time;
        this.date = date;
        Bitmap bitmap = ((BitmapDrawable) image).getBitmap();
        if (bitmap.getRowBytes() * bitmap.getHeight() > 10000000) {
            bitmap = Bitmap.createScaledBitmap(bitmap, 100, 100, true);
        }
        this.image = new BitmapDrawable(bitmap);
        this.bitmap = bitmap;
    }

    public EventProfile(String name, Drawable image) {
        this.name = name;
        this.description = " ";
        this.location = null;
        this.time = null;
        this.date = null;
        Bitmap bitmap = ((BitmapDrawable) image).getBitmap();
        if (bitmap.getRowBytes() * bitmap.getHeight() > 10000000) {
            bitmap = Bitmap.createScaledBitmap(bitmap, 100, 100, true);
        }
        this.image = new BitmapDrawable(bitmap);
        this.bitmap = bitmap;
    }

    public EventProfile(String date, String address, String name , String time) {
        this.name = name;
        this.location = address;
        this.time = time;
        this.date = date;
        this.image = null;
        this.bitmap = null;
        this.description = " ";
    }

    public EventProfile(Parcel in) {

        this.name = in.readString();
        Bitmap bitmap = (Bitmap) in.readParcelable(getClass().getClassLoader());
        this.image = new BitmapDrawable(bitmap);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        Bitmap bitmap = (Bitmap) ((BitmapDrawable) image).getBitmap();
        dest.writeParcelable(bitmap, flags);
    }

    public Drawable getImage() {
        return this.image;
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
    public void setLocation(String location) {
        this.location = location;
    }
    public void setTime(String time) {
        this.time = time;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public void setDescription(String description) { this.description = description;}

    public String getLocation() {
        return this.location;
    }
    public String getTime() {return this.time;}
    public String getDate() {return this.date;}
    public String getDescription() {return this.description;}

    public String toString() {
        String string = "Date: " + this.date + ", Location: " + this.location +
                ", Name: " + this.name + ", Time: " + this.time;
        return string;
    }
}

