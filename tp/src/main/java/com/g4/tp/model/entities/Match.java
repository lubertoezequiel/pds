package com.g4.tp.model.entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.g4.tp.model.state.MatchStateEnum;
import com.g4.tp.model.strategy.IMatchingStrategy;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name = "matches")
public class Match {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "sport_id", nullable = false)
    private Sport sport;

    private int duration;
    private LocalDateTime date;
    private LocalDateTime time;

    @Embedded
    private Location location;

    @ManyToMany
    @JoinTable(name = "match_players", joinColumns = @JoinColumn(name = "match_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<User> players = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private MatchStateEnum stateEnum;

    @Transient
    private IMatchingStrategy matchingStrategy;

    public Match() {
        this.date = LocalDateTime.now();
        this.duration = 0;
        this.matchingStrategy = null;
        this.sport = null;
        this.stateEnum = MatchStateEnum.NEED_PLAYER;
        this.time = LocalDateTime.now();
    }

    public Match(Sport sport, int duration, LocalDateTime date, LocalDateTime time) {
        this.date = date;
        this.duration = duration;
        this.matchingStrategy = null;
        this.sport = sport;
        this.stateEnum = MatchStateEnum.NEED_PLAYER;
        this.time = time;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Sport getSport() {
        return sport;
    }

    public void setSport(Sport sport) {
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

    public MatchStateEnum getStateEnum() {
        return stateEnum;
    }

    public void setStateEnum(MatchStateEnum stateEnum) {
        this.stateEnum = stateEnum;
    }

    public IMatchingStrategy getMatchingStrategy() {
        return matchingStrategy;
    }

    public void setMatchingStrategy(IMatchingStrategy matchingStrategy) {
        this.matchingStrategy = matchingStrategy;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public List<User> getPlayers() {
        return players;
    }

    public void setPlayers(List<User> players) {
        this.players = players;
    }

    public void addPlayer(User user) {
        this.players.add(user);
    }
}