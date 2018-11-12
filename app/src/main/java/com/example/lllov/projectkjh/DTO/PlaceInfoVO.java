package com.example.lllov.projectkjh.DTO;

import com.google.gson.annotations.SerializedName;

public class PlaceInfoVO {
    @SerializedName("title")
    private String title;
    @SerializedName("content")
    private String content;
    @SerializedName("imageUrl")
    private String imageUrl;

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
