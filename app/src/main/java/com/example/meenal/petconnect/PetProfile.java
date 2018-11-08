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

public class PetProfile implements Parcelable {

    private String name;
    private Drawable image;
    private Bitmap bitmap;
    private String location;
    private String age;
    private String breed;
    private String description;
    private String fee;
    private String pet;
    private String fixed;
    private String size;
    private String childFriendly;

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public static final Parcelable.Creator<PetProfile> CREATOR = new Parcelable.Creator<PetProfile>() {
        public PetProfile createFromParcel(Parcel in) {
            return new PetProfile(in);
        }

        public PetProfile[] newArray(int size) {
            return new PetProfile[size];
        }
    };

    public PetProfile() {
        this.name = "animal";
        this.location = "";
        this.age = "0";
        this.breed = "";
        this.image = null;
        this.bitmap = null;
        this.description = "Pet";
        this.fee = "0";
        this.pet = "Dog";
        this.fixed= "No";
        this.size="";
        this.childFriendly = "No";
    }
    public PetProfile(String name, Drawable image) {
        this.name = name;
        Bitmap bitmap = ((BitmapDrawable) image).getBitmap();
        if (bitmap.getRowBytes() * bitmap.getHeight() > 10000000) {
            bitmap = Bitmap.createScaledBitmap(bitmap, 100, 100, true);
        }
        this.image = new BitmapDrawable(bitmap);
        this.bitmap = bitmap;
        this.description = "Pet";
        this.fee = "0";
        this.pet = "Dog";
        this.fixed= "No";
        this.size="";
        this.childFriendly = "No";
        this.age = "0";
        this.breed = "";
        this.location = "";
    }

    public PetProfile(String name, String age, String description, String fixed, String fee, String childFriendly,String breed, String location, String pet, String size) {
        this.name = name;
//        Bitmap bitmap = ((BitmapDrawable) image).getBitmap();
//        if (bitmap.getRowBytes() * bitmap.getHeight() > 10000000) {
//            bitmap = Bitmap.createScaledBitmap(bitmap, 100, 100, true);
//        }
//        this.image = new BitmapDrawable(bitmap);
//        this.bitmap = bitmap;
        this.description = description;
        this.fee = fee;
        this.pet = pet;
        this.fixed= fixed;
        this.size=size;
        this.childFriendly = childFriendly;
        this.age = age;
        this.breed = breed;
        this.location = location;
        this.image = null;
        this.bitmap = null;
    }


    public PetProfile(Parcel in) {

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
}
