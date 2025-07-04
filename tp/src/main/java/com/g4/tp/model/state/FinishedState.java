package com.g4.tp.model.state;

import com.g4.tp.model.entities.User;

public class FinishedState implements IMatchState {

    @Override
    public void cancel(MatchContext context) {
        throw new IllegalStateException("Cannot cancel finished match");
    }

    @Override
    public void joinUser(User user, MatchContext context) {
        throw new IllegalStateException("Cannot join user, match is finished");
    }

    @Override
    public void finishMatch(MatchContext context) {
        throw new IllegalStateException("Match is already finished");
    }

    @Override
    public void confirmMatch(MatchContext context) {
        throw new IllegalStateException("Cannot confirm finished match");
    }

    @Override
    public void updateProgress(MatchContext context) {
        throw new IllegalStateException("Cannot update progress of finished match");
    }

    @Override
    public String getStateName() {
        return "Finalizado";
    }

    @Override
    public void needPlayer(MatchContext matchContext) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'needPlayer'");
    }
}