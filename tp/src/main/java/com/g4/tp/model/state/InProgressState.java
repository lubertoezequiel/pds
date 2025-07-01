package com.g4.tp.model.state;

import com.g4.tp.model.entities.Match;
import com.g4.tp.model.entities.User;

public class InProgressState implements IMatchState {

    @Override
    public void cancel(Match match) {
        // No se puede cancelar un partido en progreso
        throw new IllegalStateException("Cannot cancel match in progress");
    }

    @Override
    public void joinUser(User user, Match match) {
        // No se puede unir usuarios durante el partido
        throw new IllegalStateException("Cannot join user, match is in progress");
    }

    @Override
    public void finishMatch(Match match) {
        // TODO: Transición a FinishedState
    }

    @Override
    public void confirmMatch(Match match) {
        // Ya está en progreso
        throw new IllegalStateException("Match is already in progress");
    }

    @Override
    public void updateProgress(Match match, int progress) {
        // TODO: Actualizar progreso del partido
    }

    @Override
    public String getStateName() {
        return "InProgress";
    }
}