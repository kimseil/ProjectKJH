package com.example.lllov.projectkjh.DTO;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;
import org.parceler.ParcelConstructor;

@Parcel(Parcel.Serialization.BEAN)
public class ResponseScheduleVO {
    @SerializedName("schedule")
    private ScheduleVO schedule;
    @SerializedName("location")
    private LocationVO locagtion;

    @ParcelConstructor
    public ResponseScheduleVO(ScheduleVO schedule, LocationVO locagtion) {
        this.schedule = schedule;
        this.locagtion = locagtion;
    }

    public ScheduleVO getSchedule() {
        return schedule;
    }

    public void setSchedule(ScheduleVO schedule) {
        this.schedule = schedule;
    }

    public LocationVO getLocagtion() {
        return locagtion;
    }

    public void setLocagtion(LocationVO locagtion) {
        this.locagtion = locagtion;
    }
}
