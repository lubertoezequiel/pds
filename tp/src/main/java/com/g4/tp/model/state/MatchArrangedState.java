package com.g4.tp.model.state;

import com.g4.tp.model.state.MatchContext;
import com.g4.tp.model.entities.User;

public class MatchArrangedState implements IMatchState {

    @Override
    public void cancel(MatchContext context) {
        context.setCurrentState(new CancelledState());
    }

    @Override
    public void joinUser(User user, MatchContext context) {
        throw new IllegalStateException("Cannot join user, match is already arranged");
    }

    @Override
    public void finishMatch(MatchContext context) {
        throw new IllegalStateException("Cannot finish match in MatchArranged state");
    }

    @Override
    public void confirmMatch(MatchContext context) {
        // Transición a estado confirmado
        context.setCurrentState(new ConfirmedState());
    }

    @Override
    public void updateProgress(MatchContext context, int progress) {
        throw new IllegalStateException("Cannot update progress in MatchArranged state");
    }

    @Override
    public String getStateName() {
        return "Partido armado";
    }
}