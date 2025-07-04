package com.g4.tp.model.strategy;

import java.util.List;

import com.g4.tp.model.entities.Match;
import com.g4.tp.model.entities.User;

public interface IMatchingStrategy {

    String getName();
    List<User> matchPlayers( List<User> users, Match match);
    boolean isApplicable(Match match);
}