package com.g4.tp.model.state;

import com.g4.tp.model.entities.Match;
import com.g4.tp.model.entities.User;

public class NeedPlayerState implements IMatchState {

    @Override
    public void cancel(Match match) {
        // TODO: Transición a CancelledState
    }

    @Override
    public void joinUser(User user, Match match) {
        // TODO: Agregar usuario al partido
        // TODO: Si se completa, transición a MatchArranged
    }

    @Override
    public void finishMatch(Match match) {
        // No se puede finalizar en este estado
        throw new IllegalStateException("Cannot finish match in NeedPlayer state");
    }

    @Override
    public void confirmMatch(Match match) {
        // No se puede confirmar en este estado
        throw new IllegalStateException("Cannot confirm match in NeedPlayer state");
    }

    @Override
    public void updateProgress(Match match, int progress) {
        // No se puede actualizar progreso en este estado
        throw new IllegalStateException("Cannot update progress in NeedPlayer state");
    }
}