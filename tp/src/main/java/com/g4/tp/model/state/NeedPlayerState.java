package com.g4.tp.model.state;

import com.g4.tp.model.entities.User;

public class NeedPlayerState implements IMatchState {

    @Override
    public void cancel(MatchContext context) {
        context.setCurrentState(new CancelledState());
    }

    @Override
    public void joinUser(User user, MatchContext context) {
        // VALIDACIÓN: Evitar usuarios duplicados
        if (context.getMatch().getPlayers().contains(user)) {
            throw new IllegalStateException("El usuario ya está registrado en este partido");
        }

        context.getMatch().addPlayer(user);

        // Transición automática cuando se alcanza el número requerido
        if (context.getMatch().getPlayers().size() >= context.getMatch().getSport().getRequiredPlayers()) {
            context.setCurrentState(new MatchArrangedState());
        }
    }

    @Override
    public void finishMatch(MatchContext context) {
        throw new IllegalStateException("Cannot finish match in NeedPlayer state");
    }

    @Override
    public void confirmMatch(MatchContext context) {
        throw new IllegalStateException("Cannot confirm match in NeedPlayer state");
    }

    @Override
    public void updateProgress(MatchContext context, int progress) {
        throw new IllegalStateException("Cannot update progress in NeedPlayer state");
    }

    @Override
    public String getStateName() {
        return "Necesitamos jugadores";
    }
}