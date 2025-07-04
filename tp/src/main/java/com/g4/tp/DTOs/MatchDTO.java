package com.g4.tp.DTOs;

import java.time.LocalDateTime;

public class MatchDTO {

    private int id; 
    private int sport;
    private int duration;
    private LocalDateTime startTime;
    private int [] idPlayers;
    private LocationDTO location;
    private String strategy;
    private String state;

    public MatchDTO(int sport, int duration, LocalDateTime startTime) {

        this.sport = sport;
        this.duration = duration;
        this.startTime = startTime;

    }

    public int getSport() {
        return sport;
    }

    public void setSport(int sport) {
        this.sport = sport;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public LocalDateTime getStartTime() {
        return this.startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int[] getIdPlayers() {
        return idPlayers;
    }
    public void setIdPlayers(int[] idPlayers) {
        this.idPlayers = idPlayers;
    }
    public LocationDTO getLocation() {
        return location;
    }
    public void setLocation(LocationDTO location) {
        this.location = location;
    }    
    public String getStrategy() {
        return strategy;
    }
    public void setStrategy(String strategy) {
        this.strategy = strategy;
    }
    public String getState() {
        return state;
    }
    public void setState(String state) {
        this.state = state;
    }
}