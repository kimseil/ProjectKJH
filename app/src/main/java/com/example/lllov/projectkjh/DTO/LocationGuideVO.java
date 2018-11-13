package com.example.lllov.projectkjh.DTO;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;
import org.parceler.ParcelClass;
import org.parceler.ParcelConstructor;

@Parcel(Parcel.Serialization.BEAN)
public class LocationGuideVO {
    @SerializedName("isFavorite")
    private boolean isFavorite;
    @SerializedName("locationGuide")
    private LocationGuide locationGuide;

    @Parcel(Parcel.Serialization.BEAN)
    public static class LocationGuide {
        @SerializedName("id")
        private int id;
        @SerializedName("type")
        private int type;
        @SerializedName("title")
        private String title;
        @SerializedName("intro")
        private String intro;
        @SerializedName("imageUrl")
        private String imageUrl;
        @SerializedName("locationId")
        private int locationId;

        public int getId() {
            return id;
        }

        public int getType() {
            return type;
        }

        public String getTitle() {
            return title;
        }

        public String getIntro() {
            return intro;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public int getLocationId() {
            return locationId;
        }

        public void setId(int id) {
            this.id = id;
        }

        public void setType(int type) {
            this.type = type;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public void setIntro(String intro) {
            this.intro = intro;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }

        public void setLocationId(int locationId) {
            this.locationId = locationId;
        }
    }

    @ParcelConstructor
    public LocationGuideVO(boolean isFavorite, LocationGuide locationGuide) {
        this.isFavorite = isFavorite;
        this.locationGuide = locationGuide;
    }

    public boolean getIsFavorite() {
        return isFavorite;
    }

    public void setIsFavorite(boolean favorite) {
        isFavorite = favorite;
    }

    public LocationGuide getLocationGuide() {
        return locationGuide;
    }

    public void setLocationGuide(LocationGuide locationGuide) {
        this.locationGuide = locationGuide;
    }
}
