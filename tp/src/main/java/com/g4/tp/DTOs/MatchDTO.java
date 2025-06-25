package com.g4.tp.DTOs;

import java.time.LocalDateTime;

public class MatchDTO {

    private String sport;
    private int duration;
    private LocalDateTime date;
    private LocalDateTime time;

    public MatchDTO(String sport, int duration, LocalDateTime date, LocalDateTime time) {

        this.sport = sport;
        this.duration = duration;
        this.date = date;
        this.time = time;
    }

    public String getSport() {
        return sport;
    }

    public void setSport(String sport) {
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

}