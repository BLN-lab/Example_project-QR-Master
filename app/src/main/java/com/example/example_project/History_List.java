package com.example.example_project;

import android.media.Image;

public class History_List {
    String Titles;
    String Dates;
    int Images;

    public History_List(String titles, String dates, int images) {
        Titles = titles;
        Dates = dates;
        Images = images;
    }

    public String getTitles() {
        return Titles;
    }

    public void setTitles(String titles) {
        Titles = titles;
    }

    public String getDates() {
        return Dates;
    }

    public void setDates(String dates) {
        Dates = dates;
    }

    public int getImages() {
        return Images;
    }

    public void setImages(int images) {
        Images = images;
    }
}
