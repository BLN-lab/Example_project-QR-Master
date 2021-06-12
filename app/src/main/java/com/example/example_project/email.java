package com.example.example_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class email extends AppCompatActivity {

    Button output, save;
    ImageView img;
    EditText edit,edit2;
    OutputStream outputStream;
    DB db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email);
        db = new DB(this);

        output = findViewById(R.id.mail_build);
        img = findViewById(R.id.mail_img);
        edit = findViewById(R.id.mail_To);
        edit2 = findViewById(R.id.mail_Txt);
        save = findViewById(R.id.mail_save);

        output.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edit.getText() != null) {
                    String s_txt = edit.getText().toString()+"\n"+edit2.getText().toString();
                    MultiFormatWriter write = new MultiFormatWriter();
                    try {
                        BitMatrix matrix = write.encode(s_txt, BarcodeFormat.QR_CODE, 400, 400);
                        BarcodeEncoder encode = new BarcodeEncoder();
                        Bitmap bitmap = encode.createBitmap(matrix);
                        img.setImageBitmap(bitmap);
                        InputMethodManager manager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                        manager.hideSoftInputFromWindow(edit.getApplicationWindowToken(), 0);
                    } catch (WriterException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (img.getDrawable() != null) {
                    BitmapDrawable drawable = (BitmapDrawable) img.getDrawable();
                    Bitmap bitmap = drawable.getBitmap();
                    File path = Environment.getExternalStorageDirectory();
                    File dir = new File(path.getAbsolutePath() + "/Demo/");
                    dir.mkdir();
                    long n = System.currentTimeMillis();
                    String name = n + ".jpg";
                    File file = new File(dir, name);
                    try {
                        outputStream = new FileOutputStream(file);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
                    try {
                        outputStream.flush();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    try {
                        outputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Add(name);
                }
            }
        });
    }

    private void Add(String name) {
        String d = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
        boolean b = db.insertData(name, d);
        if (b) {
            //Toast.makeText(One_Line.this, name+d, Toast.LENGTH_SHORT).show();
            Toast.makeText(email.this, "Амжилттай боллоо", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(email.this, "Амжилтгүй боллоо", Toast.LENGTH_SHORT).show();
        }
    }
}