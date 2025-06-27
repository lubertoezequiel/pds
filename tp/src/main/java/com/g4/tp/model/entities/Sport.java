package com.g4.tp.model.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "sports")
public class Sport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long number;
    private String name;
    private String description;
    private int requiredPlayers;

    public Sport() {
        // Default constructor for JPA
    }

    public Sport(String name, String description, int requiredPlayers) {

        this.name = name;
        this.description = description;
        this.requiredPlayers = requiredPlayers;
    }

    public Long getNumber() {
        return number;
    }

    public void setId(Long number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getRequiredPlayers() {
        return requiredPlayers;
    }

    public void setRequiredPlayers(int requiredPlayers) {
        this.requiredPlayers = requiredPlayers;
    }

}