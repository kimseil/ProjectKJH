package com.example.lllov.projectkjh.DTO;

public class DTOPlaceInfo {
    private String picture;
    private String title;
    private String content;

    public DTOPlaceInfo(String picture, String title, String content) {
        this.picture = picture;
        this.title = title;
        this.content = content;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
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
