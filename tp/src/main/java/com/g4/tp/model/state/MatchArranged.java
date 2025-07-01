package com.g4.tp.model.state;

import java.util.Scanner;

import com.g4.tp.model.entities.Match;
import com.g4.tp.model.entities.User;

public class MatchArranged implements IMatchState {

    @Override
    public void cancel(Match match) {

    }

    @Override
    public void joinUser(User user, Match match) {

    }

    @Override
    public void finishMatch(Match match) {

    }

    @Override
    public void confirmMatch(Match match) {
    Scanner scanner = new Scanner(System.in);

    System.out.print("¿Confirmar partido? (y/n): ");
    String input = scanner.nextLine();

    if (input.equalsIgnoreCase("y")) {
        System.out.println("✅ Partido confirmado.");
        match.setState(new ConfirmedState());
    } else {
        System.out.println("Confirmación cancelada.");
    }
        
    }

    @Override
    public void updateProgress(Match match, int progress) {

    }

    @Override
    public String getStateName() {
        return "MatchArranged";
    }
}