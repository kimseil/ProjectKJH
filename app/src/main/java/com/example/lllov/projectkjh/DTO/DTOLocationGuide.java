package com.example.lllov.projectkjh.DTO;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class DTOLocationGuide implements Parcelable {
    String picture;
    String title;
    ArrayList<DTOInfo> info;

    public DTOLocationGuide(String picture, String title, ArrayList<DTOInfo> info) {
        this.picture = picture;
        this.title = title;
        this.info = info;
    }

    protected DTOLocationGuide(Parcel in) {
        picture = in.readString();
        title = in.readString();
        info = in.readArrayList(null);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(picture);
        dest.writeString(title);
        dest.writeList(info);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<DTOLocationGuide> CREATOR = new Creator<DTOLocationGuide>() {
        @Override
        public DTOLocationGuide createFromParcel(Parcel in) {
            return new DTOLocationGuide(in);
        }

        @Override
        public DTOLocationGuide[] newArray(int size) {
            return new DTOLocationGuide[size];
        }
    };

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
