package com.g4.tp.model.state;

import com.g4.tp.model.entities.Match;
import com.g4.tp.model.entities.User;

public class FinishedState implements IMatchState {

    @Override
    public void cancel(Match match) {
        // No se puede cancelar un partido finalizado
        throw new IllegalStateException("Cannot cancel finished match");
    }

    @Override
    public void joinUser(User user, Match match) {
        // No se puede unir usuarios a un partido finalizado
        throw new IllegalStateException("Cannot join user, match is finished");
    }

    @Override
    public void finishMatch(Match match) {
        // Ya está finalizado
        throw new IllegalStateException("Match is already finished");
    }

    @Override
    public void confirmMatch(Match match) {
        // Ya está finalizado
        throw new IllegalStateException("Cannot confirm finished match");
    }

    @Override
    public void updateProgress(Match match, int progress) {
        // No se puede actualizar progreso de un partido finalizado
        throw new IllegalStateException("Cannot update progress of finished match");
    }
}