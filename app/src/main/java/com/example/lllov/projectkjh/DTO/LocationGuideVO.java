package com.example.lllov.projectkjh.DTO;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

@Parcel(Parcel.Serialization.BEAN)
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

    public void setId(int id) {
        this.id = id;
    }

    public void setType(int type) {
        this.type = type;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }
}
