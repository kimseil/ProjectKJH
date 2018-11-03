package com.example.lllov.projectkjh.DTO;

import com.google.gson.annotations.SerializedName;

public class DTOLocationInfo {
    @SerializedName("title")
    private String title;
    @SerializedName("content")
    private String content;
    @SerializedName("id")
    private int id;

    public DTOLocationInfo(String title, String content, int id) {
        this.title = title;
        this.content = content;
        this.id = id;
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
}
