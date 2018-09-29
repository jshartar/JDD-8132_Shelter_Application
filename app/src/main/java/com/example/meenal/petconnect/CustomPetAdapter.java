package com.example.meenal.petconnect;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomPetAdapter extends ArrayAdapter<PetProfile> implements View.OnClickListener {

    private ArrayList<PetProfile> dataSet;
    Context context;

    private static class ViewHolder {
        TextView petName;
        ImageView petImage;
    }

    public CustomPetAdapter(ArrayList<PetProfile> data, Context con) {
        super(con, R.layout.activity_browse_pets, data);
        this.dataSet = data;
        this.context = con;
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        PetProfile pet = getItem(position);
        ViewHolder viewHolder;

        final View result;

        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.content_browse_pets, parent, false);
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
        viewHolder.petImage.setOnClickListener(this);
        viewHolder.petImage.setTag(position);

        return convertView;

    }

}
