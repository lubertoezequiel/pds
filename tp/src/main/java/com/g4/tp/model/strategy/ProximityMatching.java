package com.g4.tp.model.strategy;

import java.util.List;
import com.g4.tp.model.entities.User;
import com.g4.tp.model.entities.Match;

public class ProximityMatching implements IMatchingStrategy {

    private double maxDistance;

    public ProximityMatching(double maxDistance) {
        this.maxDistance = maxDistance;
    }

    @Override
    public List<User> matchPlayers(List<User> availableUsers, Match match) {
        // TODO: Implementar lógica de matching por proximidad geográfica
        return availableUsers; // Placeholder
    }

    // Getters and Setters
    public double getMaxDistance() {
        return maxDistance;
    }

    public void setMaxDistance(double maxDistance) {
        this.maxDistance = maxDistance;
    }
}