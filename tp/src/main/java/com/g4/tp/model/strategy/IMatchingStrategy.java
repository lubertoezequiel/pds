package com.g4.tp.model.strategy;

import java.util.List;
import com.g4.tp.model.entities.User;
import com.g4.tp.model.entities.Match;

public interface IMatchingStrategy {
    List<User> matchPlayers(List<User> availableUsers, Match match);

    // TODO: Implementar las siguientes estrategias:
    // - SkillLevelMatching
    // - ProximityMatching
    // - HistoryMatching
}