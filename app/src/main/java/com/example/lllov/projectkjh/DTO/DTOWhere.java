package com.example.lllov.projectkjh.DTO;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class DTOWhere {
    @SerializedName("locationGroupList")
    private ArrayList<DTOLocationGroup> locationGroupList;
    @SerializedName("listLocationList")
    private ArrayList<DTOListLocation> listLocationList;

    public ArrayList<DTOLocationGroup> getLocationGroupList() {
        return locationGroupList;
    }

    public ArrayList<DTOListLocation> getListLocationList() {
        return listLocationList;
    }
}
