package com.g4.tp.model.state;

import java.time.LocalDateTime;

import com.g4.tp.model.entities.User;

public class InProgressState implements IMatchState {

    @Override
    public void cancel(MatchContext context) {
        throw new IllegalStateException("Cannot cancel match in progress");
    }

    @Override
    public void joinUser(User user, MatchContext context) {
        throw new IllegalStateException("Cannot join user, match is in progress");
    }

    @Override
    public void finishMatch(MatchContext context) {
        context.setCurrentState(new FinishedState());
    }

    @Override
    public void confirmMatch(MatchContext context) {
        throw new IllegalStateException("Match is already in progress");
    }

    @Override
    public void updateProgress(MatchContext context) {
        LocalDateTime now = LocalDateTime.now();
        if (now.isAfter(context.getMatch().getStartTime().plusMinutes(context.getMatch().getDuration()))) {
            context.setCurrentState(new InProgressState());
        }
    }

    @Override
    public String getStateName() {
        return "En juego";
    }
}