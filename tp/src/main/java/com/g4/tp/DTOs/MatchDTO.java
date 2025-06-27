package com.g4.tp.DTOs;

import java.time.LocalDateTime;

public class MatchDTO {

    private int id; 
    private int sport;
    private int duration;
    private LocalDateTime date;
    private LocalDateTime time;
    private int [] idPlayers;
    private LocationDTO location;


    public MatchDTO(int sport, int duration, LocalDateTime date, LocalDateTime time) {

        this.sport = sport;
        this.duration = duration;
        this.date = date;
        this.time = time;
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

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
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
}