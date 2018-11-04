package com.example.lllov.projectkjh.DTO;

import com.google.gson.annotations.SerializedName;

public class DTOLocation {
    @SerializedName("name")
    private String name;
    @SerializedName("imageUrl")
    private String imageUrl;
    @SerializedName("id")
    private int id;
    @SerializedName("groupid")
    private int groupid;

    public DTOLocation(String name, String imageUrl, int id, int groupid) {
        this.name = name;
        this.imageUrl = imageUrl;
        this.id = id;
        this.groupid = groupid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGroupid() {
        return groupid;
    }

    public void setGroupid(int groupid) {
        this.groupid = groupid;
    }
}
