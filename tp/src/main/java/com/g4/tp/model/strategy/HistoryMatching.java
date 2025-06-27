package com.g4.tp.model.strategy;

import java.util.List;

import org.springframework.stereotype.Component;

import com.g4.tp.model.entities.Match;
import com.g4.tp.model.entities.User;

@Component
public class HistoryMatching implements IMatchingStrategy {

    private int minMatchesPlayed = 5;

    public HistoryMatching() {
   
    }
       @Override
    public String getName() {
        return "History";
    }

    @Override
    public List<User> matchPlayers(List<User> availableUsers, Match match) {
        // TODO: Implementar lógica de matching por historial de partidos
        return availableUsers; // Placeholder
    }

    // Getters and Setters
    public int getMinMatchesPlayed() {
        return minMatchesPlayed;
    }

    public void setMinMatchesPlayed(int minMatchesPlayed) {
        this.minMatchesPlayed = minMatchesPlayed;
    }
}