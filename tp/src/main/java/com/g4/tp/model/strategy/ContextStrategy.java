package com.g4.tp.model.strategy;

import java.util.List;

import org.springframework.stereotype.Component;

import com.g4.tp.model.entities.Match;
import com.g4.tp.model.entities.User;


@Component
public class ContextStrategy {

    private IMatchingStrategy strategy;

    public ContextStrategy() {
        // Constructor vacío para Spring
    }

    public ContextStrategy(IMatchingStrategy strategy) {
        this.strategy = strategy;
    }

    public IMatchingStrategy getStrategy() {
        return strategy;
    }

 
    public void setStrategy(IMatchingStrategy strategy) {
        this.strategy = strategy;
    }


    public List<User> executeStrategy(List<User> availableUsers, Match match) {
        if (strategy == null) {
            throw new IllegalStateException("No hay estrategia de matching configurada");
        }

        if (!strategy.isApplicable(match)) {
            throw new IllegalArgumentException(
                    String.format("La estrategia '%s' no es aplicable para este partido", strategy.getName()));
        }

        return strategy.matchPlayers(availableUsers, match);
    }


    public boolean isStrategyApplicable(Match match) {
        return strategy != null && strategy.isApplicable(match);
    }

    public String getStrategyName() {
        return strategy != null ? strategy.getName() : "No configurada";
    }

    public boolean hasStrategy() {
        return strategy != null;
    }
}