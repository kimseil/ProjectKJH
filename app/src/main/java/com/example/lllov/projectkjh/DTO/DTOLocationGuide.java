package com.example.lllov.projectkjh.DTO;

import org.parceler.Parcel;
import org.parceler.ParcelConstructor;

import java.util.ArrayList;

@Parcel(Parcel.Serialization.BEAN)
public class DTOLocationGuide {
    private LocationVO location;
    private ArrayList<LocationGuideVO> data;


    @ParcelConstructor
    public DTOLocationGuide(LocationVO location, ArrayList<LocationGuideVO> data) {
        this.location = location;
        this.data = data;
    }

    public LocationVO getLocation() {
        return location;
    }

    public void setLocation(LocationVO location) {
        this.location = location;
    }

    public ArrayList<LocationGuideVO> getData() {
        return data;
    }

    public void setData(ArrayList<LocationGuideVO> data) {
        this.data = data;
    }
}
