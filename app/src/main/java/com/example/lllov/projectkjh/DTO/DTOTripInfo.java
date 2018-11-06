package com.example.lllov.projectkjh.DTO;

public class DTOTripInfo {
    public int drawableId;
    public String location, period;

    public DTOTripInfo(int drawableId, String location, String period) {
        this.drawableId = drawableId;
        this.location = location;
        this.period = period;
    }
}
