package com.example.meenal.petconnect;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class CustomPetAdapter extends ArrayAdapter<PetProfile> {

    private ArrayList<PetProfile> dataSet;
    private Context context;
    private int pos = 0;

    private static class ViewHolder {
        TextView petName;
        ImageView petImage;
    }

    public CustomPetAdapter(ArrayList<PetProfile> data, Context con) {
        super(con, R.layout.row_item, data);
        this.dataSet = data;
        this.context = con;
    }

    /*
    @Override
    public void onClick(View v) {
        Intent intent = new Intent(getContext(), PetProfile_Activity.class);
//        PetProfile pet =  new PetProfile(this.dataSet.get(pos).getName(), this.dataSet.get(pos).getImage());
//        Bundle bundle = new Bundle();
//        bundle.putParcelable("pet", pet);
//        intent.putExtras(bundle);
        intent.putExtra("name", this.dataSet.get(pos).getName());
        intent.putExtra("image", this.dataSet.get(pos).getBitmap());
        getContext().startActivity(intent);

    }
    */

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        PetProfile pet = getItem(position);
        ViewHolder viewHolder;
        this.pos = position;

        final View result;

        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.row_item, parent, false);
            viewHolder.petName = (TextView) convertView.findViewById(R.id.name);
            viewHolder.petImage = (ImageView) convertView.findViewById(R.id.image);
            result = convertView;
            convertView.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result=convertView;
        }

        viewHolder.petName.setText(pet.getName());
        viewHolder.petImage.setImageDrawable(pet.getImage());
        //viewHolder.petImage.setOnClickListener(this);
        viewHolder.petImage.setTag(position);

        return convertView;

    }

}
