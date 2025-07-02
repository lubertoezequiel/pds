package com.g4.tp.model.state;

import com.g4.tp.model.state.MatchContext;
import com.g4.tp.model.entities.User;

public class CancelledState implements IMatchState {

    @Override
    public void cancel(MatchContext context) {
        throw new IllegalStateException("Match is already cancelled");
    }

    @Override
    public void joinUser(User user, MatchContext context) {
        throw new IllegalStateException("Cannot join user, match is cancelled");
    }

    @Override
    public void finishMatch(MatchContext context) {
        throw new IllegalStateException("Cannot finish cancelled match");
    }

    @Override
    public void confirmMatch(MatchContext context) {
        throw new IllegalStateException("Cannot confirm cancelled match");
    }

    @Override
    public void updateProgress(MatchContext context, int progress) {
        throw new IllegalStateException("Cannot update progress of cancelled match");
    }

    @Override
    public String getStateName() {
        return "Cancelado";
    }
}