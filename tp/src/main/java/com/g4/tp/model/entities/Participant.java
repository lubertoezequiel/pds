package com.g4.tp.model.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Participant {

    public static final String PARTICIPATIONSTATUS = null;

    @Id @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Enumerated(EnumType.STRING)
    private PARTICIPATIONSTATUS status;

    @ManyToOne
    @JoinColumn(name = "match_id", nullable = false)
    private Match match;
    

    public Participant() {
        // Constructor por defecto
    }
    public Participant(User user, Match match,PARTICIPATIONSTATUS status) {
        this.user = user;
        this.status = status;
        this.match = match;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
    public PARTICIPATIONSTATUS getStatus() {
        return status;
    }
    public void setStatus(PARTICIPATIONSTATUS status) {
        this.status = status;
    }
    public Match getMatch() {
        return match;
    }
    public void setMatch(Match match) {
        this.match = match;
    }

}
