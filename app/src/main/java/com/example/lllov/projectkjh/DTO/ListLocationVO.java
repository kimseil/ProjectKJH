package com.example.lllov.projectkjh.DTO;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ListLocationVO {
    @SerializedName("locationList")
    private ArrayList<LocationVO> locationList;

    public ArrayList<LocationVO> getLocationList() {
        return locationList;
    }
}
