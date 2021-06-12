package com.example.example_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class Option_List extends AppCompatActivity {

    ListView listView;
    String[] value = new String[]{"Phone", "Text", "URL", "Youtube", "Facebook", "Instagram", "SMS", "E-Mail"};
    int[] images = {R.drawable.phone, R.drawable.txt, R.drawable.url, R.drawable.youtube, R.drawable.facebook, R.drawable.instagram, R.drawable.sms, R.drawable.email};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option__list);

        listView = findViewById(R.id.list);

        ArrayList<Person> arrayList = new ArrayList<>();
        int n = value.length;
        for (int i = 0; i < n; i++) {
            arrayList.add(new Person(images[i], value[i]));
        }

        Custom_Adapter custom_adapter = new Custom_Adapter(this, R.layout.row, arrayList);
        listView.setAdapter(custom_adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position != 6 && position != 7) {
                    Intent i = new Intent(view.getContext(), One_Line.class);
                    i.putExtra("title", value[position]);
                    startActivity(i);
                } else if (position == 6) {
                    Intent i = new Intent(view.getContext(), SMS.class);
                    startActivity(i);
                } else if (position == 7) {
                    Intent i = new Intent(view.getContext(), email.class);
                    startActivity(i);
                }
            }
        });
    }
}