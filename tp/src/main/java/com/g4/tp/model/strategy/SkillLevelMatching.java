
package com.g4.tp.model.strategy;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.g4.tp.model.entities.Match;
import com.g4.tp.model.entities.SkillLevelEnum;
import com.g4.tp.model.entities.User;

public class SkillLevelMatching implements IMatchingStrategy {

    private SkillLevelEnum minSkillLevel;
    private SkillLevelEnum maxSkillLevel;

    public SkillLevelMatching(SkillLevelEnum minSkillLevel, SkillLevelEnum maxSkillLevel) {
        this.minSkillLevel = minSkillLevel;
        this.maxSkillLevel = maxSkillLevel;
    }

    @Override
    public List<User> matchPlayers(List<User> availableUsers, Match match) {
        if (availableUsers == null || availableUsers.isEmpty()) {
            return new ArrayList<>();
        }

        List<User> filteredUsers = new ArrayList<>();
        
        for (User user : availableUsers) {
            if (user.getSkillLevel() != null && 
                isSkillLevelWithinRange(user.getSkillLevel())) {
                filteredUsers.add(user);
            }
        }

        // Ordenar por nivel de habilidad (principiante -> intermedio -> avanzado)
        filteredUsers.sort((u1, u2) -> u1.getSkillLevel().compareTo(u2.getSkillLevel()));

        // Retornar solo los jugadores necesarios para completar el partido
        int playersNeeded = match.getSport().getRequiredPlayers() - match.getPlayers().size();
        
        return filteredUsers.stream()
                .limit(playersNeeded)
                .collect(Collectors.toList());
    }

    private boolean isSkillLevelWithinRange(SkillLevelEnum userSkillLevel) {
        return userSkillLevel.ordinal() >= minSkillLevel.ordinal() && 
               userSkillLevel.ordinal() <= maxSkillLevel.ordinal();
    }

    // Getters and Setters
    public SkillLevelEnum getMinSkillLevel() {
        return minSkillLevel;
    }

    public void setMinSkillLevel(SkillLevelEnum minSkillLevel) {
        this.minSkillLevel = minSkillLevel;
    }

    public SkillLevelEnum getMaxSkillLevel() {
        return maxSkillLevel;
    }

    public void setMaxSkillLevel(SkillLevelEnum maxSkillLevel) {
        this.maxSkillLevel = maxSkillLevel;
    }  


    public List<User> matchPlayers(List<User> availableUsers) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'matchPlayers'");
    }
}