package com.example.example_project;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.util.Date;

import static android.database.sqlite.SQLiteDatabase.openOrCreateDatabase;


public class DB extends SQLiteOpenHelper {

    private static final String DataBase_name = "QR_history.db";
    private static final String Table_name = "QR_history";
    private static final String Col_id = "ID";
    private static final String Col_title = "TITLE";
    private static final String Col_date = "DATE";
    Context context;

    public DB(Context context) {
        super(context, DataBase_name, null, 1);
        SQLiteDatabase db = this.getWritableDatabase();
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + Table_name + " (" + Col_id + " INTEGER PRIMARY KEY AUTOINCREMENT," + Col_title + " TEXT," + Col_date + " TEXT" + ")";
        db.execSQL(query);
        Log.d(null, query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + Table_name);
        onCreate(db);

    }

    boolean insertData(String title, String date) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(Col_title, title);
        values.put(Col_date, date);

        long result = db.insert(Table_name, null, values);
        return result != -1;
    }

    Cursor getData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + Table_name + " ORDER BY ID DESC", null);
        return res;
    }
}