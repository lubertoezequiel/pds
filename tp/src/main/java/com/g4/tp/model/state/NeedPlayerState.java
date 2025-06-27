package com.g4.tp.model.state;

import com.g4.tp.model.entities.Match;
import com.g4.tp.model.entities.User;

public class NeedPlayerState implements IMatchState {

    @Override
    public void cancel(Match match) {
        match.setState(new CancelledState());
    }

    @Override
    public void joinUser(User user, Match match) {
        match.getPlayers().add(user);
        if (match.getPlayers().size() >= match.getSport().getRequiredPlayers()) {
            match.setState(new ConfirmedState());
        }
    }

    @Override
    public void finishMatch(Match match) {
        
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