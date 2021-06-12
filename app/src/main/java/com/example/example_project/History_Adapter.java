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

public class History_Adapter extends ArrayAdapter<History_List> {

    Context hContext;
    int hResources;

    public History_Adapter(@NonNull Context context, int resource, @NonNull ArrayList<History_List> objects) {
        super(context, resource, objects);
        hContext=context;
        hResources=resource;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater=LayoutInflater.from(hContext);
        convertView=layoutInflater.inflate(hResources,parent,false);

        ImageView img=convertView.findViewById(R.id.main_image);
        TextView title=convertView.findViewById(R.id.htitle);
        TextView date=convertView.findViewById(R.id.hdate);

        img.setImageResource(getItem(position).getImages());
        title.setText(getItem(position).getTitles());
        date.setText(getItem(position).getDates());

        return convertView;
    }
}
