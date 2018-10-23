package com.example.lllov.projectkjh;

import java.util.ArrayList;

public class DTOPlaceGuide {
    String picture;
    String title;
    ArrayList<DTOInfo> info;

    public ArrayList<DTOInfo> getInfo() {
        return info;
    }

    public void setInfo(ArrayList<DTOInfo> info) {
        this.info = info;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
