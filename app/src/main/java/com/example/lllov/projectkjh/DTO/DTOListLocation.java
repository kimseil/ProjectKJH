package com.example.lllov.projectkjh.DTO;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class DTOListLocation {
    @SerializedName("locationList")
    private ArrayList<DTOLocation> locationList;

    public ArrayList<DTOLocation> getLocationList() {
        return locationList;
    }
}
