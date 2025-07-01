package com.g4.tp.model.state;

import com.g4.tp.model.entities.Match;
import com.g4.tp.model.entities.User;

public class CancelledState implements IMatchState {

    @Override
    public void cancel(Match match) {
        // Ya está cancelado
        throw new IllegalStateException("Match is already cancelled");
    }

    @Override
    public void joinUser(User user, Match match) {
        // No se puede unir usuarios a un partido cancelado
        throw new IllegalStateException("Cannot join user, match is cancelled");
    }

    @Override
    public void finishMatch(Match match) {
        // No se puede finalizar un partido cancelado
        throw new IllegalStateException("Cannot finish cancelled match");
    }

    @Override
    public void confirmMatch(Match match) {
        // No se puede confirmar un partido cancelado
        throw new IllegalStateException("Cannot confirm cancelled match");
    }

    @Override
    public void updateProgress(Match match, int progress) {
        // No se puede actualizar progreso de un partido cancelado
        throw new IllegalStateException("Cannot update progress of cancelled match");
    }

    @Override
    public String getStateName() {
        return "Cancelled";
    }
}