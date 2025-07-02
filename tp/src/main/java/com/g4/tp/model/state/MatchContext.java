package com.g4.tp.model.state;

import com.g4.tp.model.entities.Match;
import com.g4.tp.model.entities.User;

public class MatchContext {
    private Match match;
    private IMatchState currentState;

    public MatchContext(Match match) {
        this.match = match;
        this.currentState = StateFactory.createState(match.getStateEnum());
    }

    public void joinUser(User user) {
        currentState.joinUser(user, this);
    }

    public void confirmMatch() {
        currentState.confirmMatch(this);
        // Actualizar el enum en la entidad Match
        this.match.setStateEnum(StateFactory.getEnumState(this.currentState));
    }

    public void cancel() {
        currentState.cancel(this);
        // Actualizar el enum en la entidad Match
        this.match.setStateEnum(StateFactory.getEnumState(this.currentState));
    }

    public void finishMatch() {
        currentState.finishMatch(this);
        // Actualizar el enum en la entidad Match
        this.match.setStateEnum(StateFactory.getEnumState(this.currentState));
    }

    public void updateProgress(int progress) {
        currentState.updateProgress(this, progress);
        // Actualizar el enum en la entidad Match
        this.match.setStateEnum(StateFactory.getEnumState(this.currentState));
    }

    public Match getMatch() {
        return match;
    }

    public IMatchState getCurrentState() {
        return currentState;
    }

    public void setCurrentState(IMatchState newState) {
        this.currentState = newState;
        this.match.setStateEnum(StateFactory.getEnumState(newState));
    }

    public String getStateName() {
        return currentState.getStateName();
    }
}