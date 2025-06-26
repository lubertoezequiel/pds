package com.g4.tp.model.state;

import com.g4.tp.model.entities.Match;
import com.g4.tp.model.entities.User;

public class MatchArranged implements IMatchState {

    @Override
    public void cancel(Match match) {
        // TODO: Transición a CancelledState
    }

    @Override
    public void joinUser(User user, Match match) {
        // Ya está completo el partido
        throw new IllegalStateException("Cannot join user, match is already arranged");
    }

    @Override
    public void finishMatch(Match match) {
        // No se puede finalizar en este estado
        throw new IllegalStateException("Cannot finish match in MatchArranged state");
    }

    @Override
    public void confirmMatch(Match match) {
        // TODO: Transición a ConfirmedState
    }

    @Override
    public void updateProgress(Match match, int progress) {
        // No se puede actualizar progreso en este estado
        throw new IllegalStateException("Cannot update progress in MatchArranged state");
    }
}