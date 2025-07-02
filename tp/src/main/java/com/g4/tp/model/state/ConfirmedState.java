package com.g4.tp.model.state;

import com.g4.tp.model.entities.User;
import java.time.LocalDateTime;

public class ConfirmedState implements IMatchState {

    @Override
    public void cancel(MatchContext context) {
        context.setCurrentState(new CancelledState());
    }

    @Override
    public void joinUser(User user, MatchContext context) {
        throw new IllegalStateException("Cannot join user, match is confirmed");
    }

    @Override
    public void finishMatch(MatchContext context) {
        context.setCurrentState(new FinishedState());
    }

    @Override
    public void confirmMatch(MatchContext context) {
        throw new IllegalStateException("Match is already confirmed");
    }

    @Override
    public void updateProgress(MatchContext context, int progress) {
        // MEJORADO: Validar si realmente es hora del partido
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime matchTime = context.getMatch().getTime();

        // Solo transicionar si ha llegado la hora del partido (con margen de 15
        // minutos)
        if (now.isAfter(matchTime.minusMinutes(15))) {
            context.setCurrentState(new InProgressState());
        }
        // Si no, simplemente actualizar el progreso sin cambiar estado
    }

    @Override
    public String getStateName() {
        return "Confirmado";
    }
}