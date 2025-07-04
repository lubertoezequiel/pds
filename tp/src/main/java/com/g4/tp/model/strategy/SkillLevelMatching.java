
package com.g4.tp.model.strategy;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.g4.tp.model.entities.Match;
import com.g4.tp.model.entities.SKILL_LEVEL_ENUM;
import static com.g4.tp.model.entities.SKILL_LEVEL_ENUM.ADVANCED;
import static com.g4.tp.model.entities.SKILL_LEVEL_ENUM.INTERMEDIATE;
import com.g4.tp.model.entities.User;
@Component
public class SkillLevelMatching implements IMatchingStrategy {

    private SKILL_LEVEL_ENUM min = INTERMEDIATE;
    private SKILL_LEVEL_ENUM max = ADVANCED;
    private boolean enableBalancing = true;

    public SkillLevelMatching() {
  
    }

    @Override
    public String getName() {
        return "SkillLevel";
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
        int playersNeeded = match.getSport().getRequiredPlayers() - match.getParticipants().size();
        
        return filteredUsers.stream()
                .limit(playersNeeded)
                .collect(Collectors.toList());
    }

    private boolean isSkillLevelWithinRange(SKILL_LEVEL_ENUM userSkillLevel) {
        return userSkillLevel.ordinal() >= min.ordinal() && 
               userSkillLevel.ordinal() <= max.ordinal();
    }

    // Getters and Setters
    public SKILL_LEVEL_ENUM getMinSkillLevel() {
        return min;
    }

    public void setMinSkillLevel(SKILL_LEVEL_ENUM minSkillLevel) {
        this.min = minSkillLevel;
    }

    public SKILL_LEVEL_ENUM getMaxSkillLevel() {
        return max;
    }

    public void setMaxSkillLevel(SKILL_LEVEL_ENUM maxSkillLevel) {
        this.max = maxSkillLevel;
    }  


    public List<User> matchPlayers(List<User> availableUsers) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'matchPlayers'");
    }

    @Override
    public boolean isApplicable(Match match) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isApplicable'");
    }
    public void setEnableBalancing(boolean enableBalancing) {
        this.enableBalancing = enableBalancing;
    }
}