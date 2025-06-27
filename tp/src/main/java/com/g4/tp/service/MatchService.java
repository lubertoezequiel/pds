package com.g4.tp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.g4.tp.model.entities.Match;
import com.g4.tp.repository.IMatchRepository;
import com.g4.tp.service.IMatchService;

@Service
public class MatchService implements IMatchService {

    @Override
    public void createMatch(com.g4.tp.service.Match match) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createMatch'");
    }

    @Override
    public com.g4.tp.service.Match getMatchById(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getMatchById'");
    }

    @Override
    public void updateMatch(Long id, com.g4.tp.service.Match match) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateMatch'");
    }

    @Override
    public void deleteMatch(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteMatch'");
    }

    @Override
    public List<com.g4.tp.service.Match> getAllMatches() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllMatches'");
    }

    @Override
    public List<com.g4.tp.service.Match> getMatchesByUser(User user) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getMatchesByUser'");
    }

    @Override
    public List<com.g4.tp.service.Match> getMatchesBySport(Sport sport) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getMatchesBySport'");
    }

    @Override
    public List<com.g4.tp.service.Match> getMatchesByLocation(Location location) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getMatchesByLocation'");
    }

    @Override
    public List<com.g4.tp.service.Match> getMatchesByDate(LocalDateTime date) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getMatchesByDate'");
    }

    @Override
    public void joinMatch(User user, Long matchId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'joinMatch'");
    }

    @Override
    public void cancelMatch(Long matchId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'cancelMatch'");
    }

    @Override
    public void confirmMatch(Long matchId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'confirmMatch'");
    }

    @Override
    public void finishMatch(Long matchId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'finishMatch'");
    }

    @Override
    public void updateMatchProgress(Long matchId, int progress) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateMatchProgress'");
    }

    @Override
    public List<User> matchPlayers(List<User> availableUsers, com.g4.tp.service.Match match) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'matchPlayers'");
    }

    @Override
    public void setMatchingStrategy(IMatchingStrategy strategy) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setMatchingStrategy'");
    }

    @Override
    public IMatchingStrategy getMatchingStrategy() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getMatchingStrategy'");
    }

    @Override
    public void setMatchState(IMatchState state) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setMatchState'");
    }

    @Override
    public IMatchState getMatchState(com.g4.tp.service.Match match) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getMatchState'");
    }

    @Override
    public void changeMatchState(com.g4.tp.service.Match match, IMatchState newState) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'changeMatchState'");
    }

    @Override
    public List<com.g4.tp.service.Match> getMatchesBySkillLevel(SkillLevelEnum minSkillLevel,
            SkillLevelEnum maxSkillLevel) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getMatchesBySkillLevel'");
    }

    @Override
    public List<com.g4.tp.service.Match> getMatchesBySportAndSkillLevel(Sport sport, SkillLevelEnum minSkillLevel,
            SkillLevelEnum maxSkillLevel) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getMatchesBySportAndSkillLevel'");
    }

    @Override
    public List<com.g4.tp.service.Match> getMatchesBySportAndLocation(Sport sport, Location location) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getMatchesBySportAndLocation'");
    }
    // Business logic related to Match
    @Autowired

}
