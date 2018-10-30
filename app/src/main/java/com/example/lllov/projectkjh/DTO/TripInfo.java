package com.example.lllov.projectkjh.DTO;

public class TripInfo {
    public int drawableId;
    public String location, period;

    public TripInfo(int drawableId, String location, String period) {
        this.drawableId = drawableId;
        this.location = location;
        this.period = period;
    }
}
