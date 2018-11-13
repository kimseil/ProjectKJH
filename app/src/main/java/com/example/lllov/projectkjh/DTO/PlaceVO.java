package com.example.lllov.projectkjh.DTO;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;
import org.parceler.ParcelConstructor;

@Parcel(Parcel.Serialization.BEAN)
public class PlaceVO {
    @SerializedName("isFavorite")
    private boolean isFavorite;
    @SerializedName("place")
    private Place place;

    @Parcel(Parcel.Serialization.BEAN)
    public static class Place {
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
        @SerializedName("latitude")
        private String latitude;
        @SerializedName("longitude")
        private String longitude;
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

        public String getLatitude() {
            return latitude;
        }

        public String getLongitude() {
            return longitude;
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

        public void setLatitude(String latitude) {
            this.latitude = latitude;
        }

        public void setLongitude(String longitude) {
            this.longitude = longitude;
        }

        public void setLocationId(int locationId) {
            this.locationId = locationId;
        }
    }

    @ParcelConstructor
    public PlaceVO(boolean isFavorite, Place place) {
        this.isFavorite = isFavorite;
        this.place = place;
    }

    public boolean getIsFavorite() {
        return isFavorite;
    }

    public void setIsFavorite(boolean favorite) {
        isFavorite = favorite;
    }

    public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) {
        this.place = place;
    }
}
