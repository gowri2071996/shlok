package com.example.androidtask;

public class PlaceModel {

    int placeID;
    String placeName;
    String placeAddress;

    public PlaceModel(int placeID, String placeName, String placeAddress) {
        this.placeID = placeID;
        this.placeName = placeName;
        this.placeAddress = placeAddress;
    }

    public int getPlaceID() {
        return placeID;
    }

    public void setPlaceID(int placeID) {
        this.placeID = placeID;
    }

    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    public String getPlaceAddress() {
        return placeAddress;
    }

    public void setPlaceAddress(String placeAddress) {
        this.placeAddress = placeAddress;
    }
}
