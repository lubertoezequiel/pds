package com.g4.tp.DTOs;


public class UserDTO {
    private int id;
    private String name;
    private String email;
    private String password;
    private int [] practicedSportIds; 
    private int favoriteSportId;         
    private String skillLevel;  
    private Double latitude;
    private Double longitude;          
    private String Address;
     // Optional, if you want to include address information
    public UserDTO() {}

    public UserDTO(String name, String email, String password, int [] practicedSportIds, int favoriteSportId, String skillLevel) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.practicedSportIds = practicedSportIds;
        this.favoriteSportId = favoriteSportId;
        this.skillLevel = skillLevel;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public int[] getPracticedSportIds() {
        return practicedSportIds;
    }
    public void setPracticedSportIds(int[] practicedSportIds) {
        this.practicedSportIds = practicedSportIds;
    }
    public int getFavoriteSportId() {
        return favoriteSportId;
    }
    public void setFavoriteSportId(int favoriteSportId) {
        this.favoriteSportId = favoriteSportId;
    }
    public String getSkillLevel() {
        return skillLevel;
    }
    public void setSkillLevel(String skillLevel) {
        this.skillLevel = skillLevel;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
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
        return Address;
    }
    public void setAddress(String address) {
        Address = address;
    }
}