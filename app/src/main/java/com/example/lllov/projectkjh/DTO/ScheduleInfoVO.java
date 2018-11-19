package com.example.lllov.projectkjh.DTO;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;
import org.parceler.ParcelConstructor;

@Parcel(Parcel.Serialization.BEAN)
public class ScheduleInfoVO {
    @SerializedName("id")
    private int id;
    @SerializedName("day")
    private long day;
    @SerializedName("type")
    private int type;
    @SerializedName("number")
    private int number;
    @SerializedName("memoId")
    private int memoId;
    @SerializedName("placeId")
    private int placeId;
    @SerializedName("scheduleId")
    private int scheduleId;

    @ParcelConstructor
    public ScheduleInfoVO(int id, long day, int type, int number, int memoId, int placeId, int scheduleId) {
        this.id = id;
        this.day = day;
        this.type = type;
        this.number = number;
        this.memoId = memoId;
        this.placeId = placeId;
        this.scheduleId = scheduleId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getDay() {
        return day;
    }

    public void setDay(long day) {
        this.day = day;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getMemoId() {
        return memoId;
    }

    public void setMemoId(int memoId) {
        this.memoId = memoId;
    }

    public int getPlaceId() {
        return placeId;
    }

    public void setPlaceId(int placeId) {
        this.placeId = placeId;
    }

    public int getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(int scheduleId) {
        this.scheduleId = scheduleId;
    }
}
