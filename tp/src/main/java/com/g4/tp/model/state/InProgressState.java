package com.g4.tp.model.state;

import com.g4.tp.model.state.MatchContext;
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
    public void updateProgress(MatchContext context, int progress) {
        // Actualizar progreso del partido - lógica específica aquí
        // Por ejemplo, actualizar tiempo transcurrido, marcador, etc.
    }

    @Override
    public String getStateName() {
        return "En juego";
    }
}