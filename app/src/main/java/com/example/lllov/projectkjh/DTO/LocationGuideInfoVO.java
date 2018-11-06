package com.example.lllov.projectkjh.DTO;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

public class LocationGuideInfoVO {
    @SerializedName("id")
    private int id;
    @SerializedName("title")
    private String title;
    @SerializedName("content")
    private String content;
    @SerializedName("imageUrl")
    private String imageUrl;
    @SerializedName("guideId")
    private int guideId;

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public int getGuideId() {
        return guideId;
    }
}
