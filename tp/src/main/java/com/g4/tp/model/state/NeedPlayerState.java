package com.g4.tp.model.state;

import java.time.LocalDateTime;

import com.g4.tp.model.entities.User;

public class NeedPlayerState implements IMatchState {

    @Override
    public void cancel(MatchContext context) {
        context.setCurrentState(new CancelledState());
    }

    @Override
    public void joinUser(User user, MatchContext context) {
        // VALIDACIÓN: Evitar usuarios duplicados
        if (context.getMatch().getParticipants().stream()
                .anyMatch(participant -> participant.getUser().getId() == user.getId())) {
            throw new IllegalStateException("El usuario ya está registrado en este partido");
        }

        context.getMatch().addOneParticipant(user);

        // Transición automática cuando se alcanza el número requerido
        if (context.getMatch().getParticipants().size() >= context.getMatch().getSport().getRequiredPlayers()) {
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
    public void updateProgress(MatchContext context) {
       LocalDateTime now = LocalDateTime.now();

        if(now.isAfter(context.getMatch().getStartTime()))
            context.setCurrentState(new CancelledState());
    }

    @Override
    public String getStateName() {
        return "Necesitamos jugadores";
    }

    @Override
    public void needPlayer(MatchContext matchContext) {
        if(matchContext.getMatch().getSport().getRequiredPlayers() == matchContext.getMatch().getParticipants().size()) 
            matchContext.setCurrentState(new MatchArrangedState());
        
     }
}