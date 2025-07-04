package com.g4.tp.model.state;

import com.g4.tp.model.entities.User;

public interface IMatchState {

    public void cancel(MatchContext context);

    public void joinUser(User user, MatchContext context);

    public void finishMatch(MatchContext context);

    public void confirmMatch(MatchContext context);

    public void updateProgress(MatchContext context);

    public String getStateName();

    public void needPlayer(MatchContext matchContext);

}
