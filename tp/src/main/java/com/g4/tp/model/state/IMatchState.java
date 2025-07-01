package com.g4.tp.model.state;

import com.g4.tp.model.entities.Match;
import com.g4.tp.model.entities.User;

public interface IMatchState {
   
    public void cancel(Match match);
    public void joinUser(User user, Match match);
    public void finishMatch(Match match);
    public void confirmMatch(Match match);
    public void updateProgress(Match match, int progress);
    public String getStateName();

}
