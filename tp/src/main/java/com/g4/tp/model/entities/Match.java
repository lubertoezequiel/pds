package com.g4.tp.model.entities;

import java.time.LocalDateTime;

import com.g4.tp.model.state.IMatchState;
import com.g4.tp.model.strategy.IMatchingStrategy;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;


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
    @Transient
    private IMatchState state;
    @Transient
    private IMatchingStrategy matchingStrategy;
    // TODO: Implementar patrón State más adelante

    // TODO: Agregar relaciones con User (players), Location, etc.
    // TODO: Implementar IMatchingStrategy
    // TODO: Implementar Observer pattern para notificaciones



    public Match(String sport, int duration, LocalDateTime date, LocalDateTime time) {
        this.date = date;
        this.duration = duration;
        this.id = id;
        this.matchingStrategy = matchingStrategy;
        this.sport = sport;
        this.state = state;
        this.time = time;
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



}