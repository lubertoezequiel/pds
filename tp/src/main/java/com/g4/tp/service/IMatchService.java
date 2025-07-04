package com.g4.tp.service;

import java.time.LocalDateTime;
import java.util.List;

import com.g4.tp.model.entities.Location;
import com.g4.tp.model.entities.Match;
import com.g4.tp.model.entities.SKILL_LEVEL_ENUM;
import com.g4.tp.model.entities.Sport;
import com.g4.tp.model.entities.User;
import com.g4.tp.model.state.IMatchState;
import com.g4.tp.model.strategy.IMatchingStrategy;

public interface IMatchService {

    Match createMatch(Match match);
    Match getMatchById(int id);
    void updateMatch(int id, Match match);
    void deleteMatch(int id);
    List<Match> getAllMatches();
    List<Match> getMatchesByUser(User user);
    List<Match> getMatchesBySport(Sport sport);
    List<Match> getMatchesByLocation(Location location);
    List<Match> getMatchesByDate(LocalDateTime date);
    Match joinMatch(int userId, int matchId);
    Match cancelMatch(int matchId);
    void confirmMatch(int matchId);
    void finishMatch(int matchId);
    void updateMatchProgress(Long matchId, int progress);
    List<User> matchPlayers(List<User> availableUsers, Match match);
    void setMatchingStrategy(IMatchingStrategy strategy);
    IMatchingStrategy getMatchingStrategy();
    void setMatchState(IMatchState state);
    IMatchState getMatchState(Match match);
    void changeMatchState(Match match, IMatchState newState);
    List<Match> getMatchesBySkillLevel(SKILL_LEVEL_ENUM minSkillLevel, SKILL_LEVEL_ENUM maxSkillLevel);
    List<Match> getMatchesBySportAndSkillLevel(Sport sport, SKILL_LEVEL_ENUM minSkillLevel, SKILL_LEVEL_ENUM maxSkillLevel);
    List<Match> getMatchesBySportAndLocation(Sport sport, Location location);
    List<Match> getMatchesByProximity(Location userLocation, double radius);
    List<Match> getMatchesByProximityByUserId(int userId, double radius);
    Match acceptParticipation(int matchId, int userId);

}
