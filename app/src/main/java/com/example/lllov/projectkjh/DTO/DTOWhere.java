package com.example.lllov.projectkjh.DTO;

public class DTOWhere {
    private String location;
    private String imageUrl;

    public DTOWhere(String location, String imageUrl) {
        this.location = location;
        this.imageUrl = imageUrl;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
