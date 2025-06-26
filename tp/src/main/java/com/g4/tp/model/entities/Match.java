package com.g4.tp.model.entities;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "matches")
public class Match {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String sport;
    private int duration;
    private LocalDateTime date;
    private LocalDateTime time;
    private String state; // TODO: Implementar patrón State más adelante

    // TODO: Agregar relaciones con User (players), Location, etc.
    // TODO: Implementar IMatchingStrategy
    // TODO: Implementar Observer pattern para notificaciones

    public Match(String sport, int duration, LocalDateTime date, LocalDateTime time) {

        this.sport = sport;
        this.duration = duration;
        this.date = date;
        this.time = time;
        this.state = "Necesitamos jugadores"; // Estado inicial
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

}