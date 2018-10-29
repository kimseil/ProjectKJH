package com.example.lllov.projectkjh.DTO;

public class DTORecommned {
    private String title;
    private String content;
    private String location;
    private String picture;

    public DTORecommned(String title, String content, String location, String picture) {
        this.title = title;
        this.content = content;
        this.location = location;
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }
}
