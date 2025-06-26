package com.g4.tp.model.state;

import com.g4.tp.model.entities.Match;
import com.g4.tp.model.entities.User;

public interface IMatchState {

    void cancel(Match match);

    void joinUser(User user, Match match);

    void finishMatch(Match match);

    void confirmMatch(Match match);

    void updateProgress(Match match, int progress);

    // TODO: Implementar los siguientes estados:
    // - NeedPlayerState
    // - MatchArranged
    // - ConfirmedState
    // - InProgressState
    // - FinishedState
    // - CancelledState
}