package com.example.lllov.projectkjh.DTO;

import com.google.gson.annotations.SerializedName;

public class LocationInfoVO {
    @SerializedName("title")
    private String title;
    @SerializedName("content")
    private String content;
    @SerializedName("id")
    private int id;
    @SerializedName("locationId")
    private int locationId;

    public LocationInfoVO(String title, String content, int id, int locationId) {
        this.title = title;
        this.content = content;
        this.id = id;
        this.locationId = locationId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }
}
