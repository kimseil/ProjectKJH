package com.example.lllov.projectkjh.DTO;

public class DTOAddPlace {
    private String picture;
    private String place;
    private String location;

    public DTOAddPlace(String picture, String place, String location) {
        this.picture = picture;
        this.place = place;
        this.location = location;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
