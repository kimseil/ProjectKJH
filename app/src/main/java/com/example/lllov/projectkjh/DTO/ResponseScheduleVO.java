package com.example.lllov.projectkjh.DTO;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;
import org.parceler.ParcelConstructor;

@Parcel(Parcel.Serialization.BEAN)
public class ResponseScheduleVO {
    @SerializedName("schedule")
    private ScheduleVO schedule;
    @SerializedName("location")
    private LocationVO location;

    @ParcelConstructor
    public ResponseScheduleVO(ScheduleVO schedule, LocationVO location) {
        this.schedule = schedule;
        this.location = location;
    }

    public ScheduleVO getSchedule() {
        return schedule;
    }

    public void setSchedule(ScheduleVO schedule) {
        this.schedule = schedule;
    }

    public LocationVO getLocation() {
        return location;
    }

    public void setLocation(LocationVO location) {
        this.location = location;
    }
}
