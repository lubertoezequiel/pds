package com.g4.tp.model.strategy;

import java.util.List;

import com.g4.tp.model.entities.Match;
import com.g4.tp.model.entities.User;

public interface IMatchingStrategy {

    List<User> matchPlayers(List<User> availableUsers, Match match);

}