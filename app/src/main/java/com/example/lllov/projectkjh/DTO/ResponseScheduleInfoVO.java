package com.example.lllov.projectkjh.DTO;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;
import org.parceler.ParcelConstructor;

@Parcel(Parcel.Serialization.BEAN)
public class ResponseScheduleInfoVO {
    @SerializedName("scheduleInfo")
    private ScheduleInfoVO scheduleInfo;
    @SerializedName("title")
    private String title;
    @SerializedName("type")
    private int type;
    @SerializedName("imageUrl")
    private String imageUrl;

    @ParcelConstructor
    public ResponseScheduleInfoVO(ScheduleInfoVO scheduleInfo, String title, int type, String imageUrl) {
        this.scheduleInfo = scheduleInfo;
        this.title = title;
        this.type = type;
        this.imageUrl = imageUrl;
    }

    public ScheduleInfoVO getScheduleInfo() {
        return scheduleInfo;
    }

    public void setScheduleInfo(ScheduleInfoVO scheduleInfo) {
        this.scheduleInfo = scheduleInfo;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
