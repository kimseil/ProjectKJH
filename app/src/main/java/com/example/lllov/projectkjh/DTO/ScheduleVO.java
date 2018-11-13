package com.example.lllov.projectkjh.DTO;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;
import org.parceler.ParcelConstructor;

@Parcel(Parcel.Serialization.BEAN)
public class ScheduleVO {
    @SerializedName("id")
    private int id;
    @SerializedName("startDay")
    private long startDay;
    @SerializedName("endDay")
    private long endDay;
    @SerializedName("locationId")
    private int locationId;
    @SerializedName("userId")
    private long userId;

    @ParcelConstructor
    public ScheduleVO(int id, long startDay, long endDay, int locationId, long userId) {
        this.id = id;
        this.startDay = startDay;
        this.endDay = endDay;
        this.locationId = locationId;
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getStartDay() {
        return startDay;
    }

    public void setStartDay(long startDay) {
        this.startDay = startDay;
    }

    public long getEndDay() {
        return endDay;
    }

    public void setEndDay(long endDay) {
        this.endDay = endDay;
    }

    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
}
