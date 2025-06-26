package com.g4.tp.model.interfaces;

import java.util.List;

import com.g4.tp.model.entities.Match;
import com.g4.tp.model.entities.User;

public interface IMatchingStrategy {
    public List<User> matchPlayers(List<User> availableUsers, Match match);
}
