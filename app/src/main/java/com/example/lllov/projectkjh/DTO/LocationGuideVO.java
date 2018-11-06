package com.example.lllov.projectkjh.DTO;

import com.google.gson.annotations.SerializedName;

public class LocationGuideVO {
    @SerializedName("id")
    private int id;
    @SerializedName("type")
    private int type;
    @SerializedName("title")
    private String title;
    @SerializedName("intro")
    private String intro;
    @SerializedName("imageUrl")
    private String imageUrl;
    @SerializedName("locationId")
    private int locationId;

    public int getId() {
        return id;
    }

    public int getType() {
        return type;
    }

    public String getTitle() {
        return title;
    }

    public String getIntro() {
        return intro;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public int getLocationId() {
        return locationId;
    }
}
