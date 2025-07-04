package com.g4.tp.model.entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.g4.tp.model.state.MatchStateEnum;
import com.g4.tp.model.strategy.IMatchingStrategy;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
    private LocalDateTime startTime;


    @Embedded
    private Location location;

    @OneToMany(mappedBy = "match", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Participant> players = new ArrayList<>();

    

    @Enumerated(EnumType.STRING)
    private MatchStateEnum stateEnum;

    @Transient
    private IMatchingStrategy matchingStrategy;

    public Match() {

        this.duration = 0;
        this.matchingStrategy = null;
        this.sport = null;
        this.stateEnum = MatchStateEnum.NEED_PLAYER;
   
    }

    public Match(Sport sport, int duration, LocalDateTime date, LocalDateTime time) {
       
        this.duration = duration;
        this.matchingStrategy = null;
        this.sport = sport;
        this.stateEnum = MatchStateEnum.NEED_PLAYER;
        this.startTime = time;
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

    public void setStartTime(LocalDateTime start){
        this.startTime= start; 
    }

    public LocalDateTime getStartTime(){
        return this.startTime;
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

    public List<Participant> getParticipants() {
        return players;
    }

    public void setParticipant() {

        
    }

    public void addParticipants(List <User> players) {
        
        for (User user : players) {
            Participant newParticipant = new Participant(user, this, PARTICIPATIONSTATUS.PENDING);
            this.players.add(newParticipant);
        }

    }
    public void addOneParticipant(User user){
        Participant newParticipant = new Participant(user, this, PARTICIPATIONSTATUS.PENDING);
        this.players.add(newParticipant);
    }

    public void add(List <User> users){
        this.addParticipants(matchingStrategy.matchPlayers(users, this));
        
    }
}