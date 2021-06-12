package com.example.example_project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class Custom_Adapter extends ArrayAdapter<Person> {
    Context mContext;
    int mResources;

    public Custom_Adapter(@NonNull Context context, int resource, @NonNull ArrayList<Person> objects) {
        super(context, resource, objects);
        mContext=context;
        mResources=resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater=LayoutInflater.from(mContext);
        convertView=layoutInflater.inflate(mResources,parent,false);
        ImageView imageView=convertView.findViewById(R.id.row_img);
        TextView textView=convertView.findViewById(R.id.row_title);

        imageView.setImageResource(getItem(position).getImage());
        textView.setText(getItem(position).getTitle());
        return convertView;
    }
}
