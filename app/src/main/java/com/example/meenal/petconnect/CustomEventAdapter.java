package com.example.meenal.petconnect;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomEventAdapter extends ArrayAdapter<EventProfile> implements View.OnClickListener {

    private ArrayList<EventProfile> dataSet;
    private Context context;
    private int pos = 0;

    private static class ViewHolder {
        TextView eventName;
        ImageView eventImage;
    }

    public CustomEventAdapter(ArrayList<EventProfile> data, Context con) {
        super(con, R.layout.row_item, data);
        this.dataSet = data;
        this.context = con;
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(getContext(), EventActivity.class);
//        PetProfile pet =  new PetProfile(this.dataSet.get(pos).getName(), this.dataSet.get(pos).getImage());
//        Bundle bundle = new Bundle();
//        bundle.putParcelable("pet", pet);
//        intent.putExtras(bundle);
        intent.putExtra("name", this.dataSet.get(pos).getName());
        intent.putExtra("image", this.dataSet.get(pos).getBitmap());
        getContext().startActivity(intent);

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        EventProfile ev = getItem(position);
        CustomEventAdapter.ViewHolder viewHolder;
        this.pos = position;

        final View result;

        if (convertView == null) {
            viewHolder = new CustomEventAdapter.ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.row_item, parent, false);
            viewHolder.eventName = (TextView) convertView.findViewById(R.id.name);
            viewHolder.eventImage = (ImageView) convertView.findViewById(R.id.image);
            result = convertView;
            convertView.setTag(viewHolder);

        } else {
            viewHolder = (CustomEventAdapter.ViewHolder) convertView.getTag();
            result=convertView;
        }

        viewHolder.eventName.setText(ev.getName());
        viewHolder.eventImage.setImageDrawable(ev.getImage());
        viewHolder.eventImage.setOnClickListener(this);
        viewHolder.eventImage.setTag(position);

        return convertView;

    }

}