package com.g4.tp.model.state;

import java.time.LocalDateTime;

import com.g4.tp.model.entities.PARTICIPATIONSTATUS;
import com.g4.tp.model.entities.User;

public class MatchArrangedState implements IMatchState {

    @Override
    public void cancel(MatchContext context) {
        context.setCurrentState(new CancelledState());
    }

    @Override
    public void joinUser(User user, MatchContext context) {
        throw new IllegalStateException("Cannot join user, match is already arranged");
    }

    @Override
    public void finishMatch(MatchContext context) {
        throw new IllegalStateException("Cannot finish match in MatchArranged state");
    }

    @Override
    public void confirmMatch(MatchContext context) {
        
        if(context.getMatch().getParticipants().isEmpty()) {
            throw new IllegalStateException("Cannot confirm match with no participants");
        }

        context.getMatch().getParticipants().forEach(participant -> {
            if (participant.getStatus() == PARTICIPATIONSTATUS.PENDING) {
                return;
            }
            context.setCurrentState(new ConfirmedState());
        });


    }

    @Override
    public void updateProgress(MatchContext context) {
        LocalDateTime now = LocalDateTime.now();

        if(now.isAfter(context.getMatch().getStartTime()))
            context.setCurrentState(new CancelledState());
    }

    @Override
    public String getStateName() {
        return "Partido armado";
    }

    @Override
    public void needPlayer(MatchContext matchContext) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'needPlayer'");
    }
}