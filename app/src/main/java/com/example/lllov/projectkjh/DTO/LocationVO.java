package com.example.lllov.projectkjh.DTO;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

@Parcel(Parcel.Serialization.BEAN)
public class LocationVO {
    @SerializedName("name")
    private String name;
    @SerializedName("imageUrl")
    private String imageUrl;
    @SerializedName("id")
    private int id;
    @SerializedName("groupId")
    private int groupId;

    public String getName() {
        return name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public int getId() {
        return id;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }
}
