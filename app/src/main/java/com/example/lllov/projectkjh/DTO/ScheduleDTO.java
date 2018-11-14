package com.example.lllov.projectkjh.DTO;

import org.parceler.Parcel;
import org.parceler.ParcelConstructor;

@Parcel(Parcel.Serialization.BEAN)
public class ScheduleDTO {
    private long startDay;
    private long endDay;
    private int locationId;
    private long userId;

    @ParcelConstructor
    public ScheduleDTO(long startDay, long endDay, int locationId, long userId) {
        this.startDay = startDay;
        this.endDay = endDay;
        this.locationId = locationId;
        this.userId = userId;
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
