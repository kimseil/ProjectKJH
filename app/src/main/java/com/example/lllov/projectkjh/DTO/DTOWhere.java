package com.example.lllov.projectkjh.DTO;

import com.google.gson.annotations.SerializedName;

public class DTOWhere {
    @SerializedName("locationGroup")
    private DTOLocationGroup locationGroup;
    @SerializedName("location")
    private DTOLocation location;

    public DTOWhere(DTOLocationGroup locationGroup, DTOLocation location) {
        this.locationGroup = locationGroup;
        this.location = location;
    }

    public DTOLocationGroup getLocationGroup() {
        return locationGroup;
    }

    public void setLocationGroup(DTOLocationGroup locationGroup) {
        this.locationGroup = locationGroup;
    }

    public DTOLocation getLocation() {
        return location;
    }

    public void setLocation(DTOLocation location) {
        this.location = location;
    }
}
