package com.example.meenal.petconnect;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomEventAdapter extends ArrayAdapter<EventProfile> implements View.OnClickListener {

    private ArrayList<EventProfile> dataSet;
    Context context;

    private static class ViewHolder {
        TextView eventName;
        ImageView eventImage;
    }

    public CustomEventAdapter(ArrayList<EventProfile> data, Context con) {
        super(con, R.layout.activity_browse_events, data);
        this.dataSet = data;
        this.context = con;
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        EventProfile event = getItem(position);
        ViewHolder viewHolder;

        final View result;

        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.content_browse_events, parent, false);
            viewHolder.eventName = (TextView) convertView.findViewById(R.id.eventname);
            viewHolder.eventImage = (ImageView) convertView.findViewById(R.id.eventimage);
            result = convertView;
            convertView.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result=convertView;
        }

        viewHolder.eventName.setText(event.getName());
        viewHolder.eventImage.setImageDrawable(event.getImage());
        viewHolder.eventImage.setOnClickListener(this);
        viewHolder.eventImage.setTag(position);

        return convertView;

    }

}