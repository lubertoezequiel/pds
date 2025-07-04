package com.g4.tp.service;

import java.util.List;

import com.g4.tp.model.entities.Location;
import com.g4.tp.model.entities.Match;

public interface IMatchService {

    Match createMatch(Match match);
    Match getMatchById(int id);
    Match joinMatch(int userId, int matchId);
    Match cancelMatch(int matchId);
    List<Match> getMatchesByProximity(Location userLocation, double radius);
    List<Match> getMatchesByProximityByUserId(int userId, double radius);
    Match acceptParticipation(int matchId, int userId);

}
