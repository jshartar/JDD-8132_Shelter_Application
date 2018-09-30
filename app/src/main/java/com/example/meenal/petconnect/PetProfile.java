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

    public PetProfile(String name, Drawable image) {
        this.name = name;
        Bitmap bitmap = ((BitmapDrawable) image).getBitmap();
        if (bitmap.getRowBytes() * bitmap.getHeight() > 10000000) {
            bitmap = Bitmap.createScaledBitmap(bitmap, 100, 100, true);
        }
        this.image = new BitmapDrawable(bitmap);
        this.bitmap = bitmap;
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
