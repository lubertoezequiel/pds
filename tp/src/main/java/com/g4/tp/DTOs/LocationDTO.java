package com.g4.tp.DTOs;

public class LocationDTO {
    
    private Double latitude;
    private Double longitude;
    private String address; 

    public LocationDTO() {
        this.latitude = 0.0;
        this.longitude = 0.0;
    }

    public LocationDTO(Double latitude, Double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
}
