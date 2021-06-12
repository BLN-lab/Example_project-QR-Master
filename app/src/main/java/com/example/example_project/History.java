package com.example.example_project;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class History extends AppCompatActivity {

    int Images = R.mipmap.ic_main_icon;

    ListView lsl;
    ArrayList<History_List> a = new ArrayList();

    DB db;
    Cursor res;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        lsl = findViewById(R.id.list);

        Get();

        History_Adapter ha = new History_Adapter(this, R.layout.history_row, a);
        lsl.setAdapter(ha);
    }

    private void Get(){
        db = new DB(this);
        res = db.getData();
        if (res.getCount() == 0) {
            Toast.makeText(History.this, "Database is empty...", Toast.LENGTH_SHORT).show();
            return;
        }

        while(res.moveToNext()) {
            a.add(new History_List(res.getString(1), res.getString(2), Images));
        }
    }
}