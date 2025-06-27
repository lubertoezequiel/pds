package com.g4.tp.DTOs;

public class SportDTO {
    private int id; // Assuming you want to include an ID for the sport
    private String name;
    private String description;
    private int requiredPlayers;

    public SportDTO(String name, String description, int requiredPlayers) {

        this.name = name;
        this.description = description;
        this.requiredPlayers = requiredPlayers;
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
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

}