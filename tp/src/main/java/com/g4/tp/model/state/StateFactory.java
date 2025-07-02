package com.g4.tp.model.state;

import com.g4.tp.model.state.*;

public class StateFactory {

    public static IMatchState createState(MatchStateEnum stateEnum) {
        switch (stateEnum) {
            case NEED_PLAYER:
                return new NeedPlayerState();
            case MATCH_ARRANGED:
                return new MatchArrangedState();
            case CONFIRMED:
                return new ConfirmedState();
            case IN_PROGRESS:
                return new InProgressState();
            case FINISHED:
                return new FinishedState();
            case CANCELLED:
                return new CancelledState();
            default:
                throw new IllegalArgumentException("Estado no válido: " + stateEnum);
        }
    }

    public static MatchStateEnum getEnumState(IMatchState state) {
        if (state instanceof NeedPlayerState) {
            return MatchStateEnum.NEED_PLAYER;
        } else if (state instanceof MatchArrangedState) {
            return MatchStateEnum.MATCH_ARRANGED;
        } else if (state instanceof ConfirmedState) {
            return MatchStateEnum.CONFIRMED;
        } else if (state instanceof InProgressState) {
            return MatchStateEnum.IN_PROGRESS;
        } else if (state instanceof FinishedState) {
            return MatchStateEnum.FINISHED;
        } else if (state instanceof CancelledState) {
            return MatchStateEnum.CANCELLED;
        } else {
            throw new IllegalArgumentException("Estado no reconocido: " + state.getClass().getSimpleName());
        }
    }
}