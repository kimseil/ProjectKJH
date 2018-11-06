package com.example.lllov.projectkjh.DTO;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

//현재 안쓰이는 중
public class WhereVO {
    @SerializedName("locationGroupList")
    private ArrayList<LocationGroupVO> locationGroupList;
    @SerializedName("listLocationList")
    private ArrayList<ListLocationVO> listLocationList;

    public ArrayList<LocationGroupVO> getLocationGroupList() {
        return locationGroupList;
    }

    public ArrayList<ListLocationVO> getListLocationList() {
        return listLocationList;
    }
}
