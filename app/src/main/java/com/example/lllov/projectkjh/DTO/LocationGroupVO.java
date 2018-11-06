package com.example.lllov.projectkjh.DTO;

import com.google.gson.annotations.SerializedName;

public class LocationGroupVO {
    @SerializedName("name")
    private String name;
    @SerializedName("id")
    private int id;

    public LocationGroupVO(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
