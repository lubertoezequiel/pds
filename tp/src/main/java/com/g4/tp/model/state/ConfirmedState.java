package com.g4.tp.model.state;

import com.g4.tp.model.entities.Match;
import com.g4.tp.model.entities.User;

public class ConfirmedState implements IMatchState {

    @Override
    public void cancel(Match match) {
        // TODO: Transición a CancelledState
    }

    @Override
    public void joinUser(User user, Match match) {
        // Ya está confirmado el partido
        throw new IllegalStateException("Cannot join user, match is confirmed");
    }

    @Override
    public void finishMatch(Match match) {
        // TODO: Transición a FinishedState
    }

    @Override
    public void confirmMatch(Match match) {
        // Ya está confirmado
        throw new IllegalStateException("Match is already confirmed");
    }

    @Override
    public void updateProgress(Match match, int progress) {
        // TODO: Transición a InProgressState cuando llegue la hora
    }
}