package com.g4.tp.model.strategy;

import java.util.List;
import com.g4.tp.model.entities.User;
import com.g4.tp.model.entities.Match;

public class HistoryMatching implements IMatchingStrategy {

    private int minMatchesPlayed;

    public HistoryMatching(int minMatchesPlayed) {
        this.minMatchesPlayed = minMatchesPlayed;
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