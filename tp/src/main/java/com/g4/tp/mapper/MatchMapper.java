package com.g4.tp.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.g4.tp.DTOs.MatchDTO;
import com.g4.tp.model.entities.Match;
import com.g4.tp.model.entities.Participant;
import com.g4.tp.model.entities.Sport;
import com.g4.tp.model.entities.User;
import com.g4.tp.model.strategy.ProximityMatching;
import com.g4.tp.model.strategy.SkillLevelMatching;
import com.g4.tp.model.strategy.StrategyFactory;
import com.g4.tp.service.ISportService;
import com.g4.tp.service.IUserService;

@Component
public class MatchMapper {
    @Autowired
    private LocationMapper locationMapper;
   
    @Autowired
    private ISportService sportService;

    @Autowired
    private IUserService userService;

    @Autowired private ProximityMatching proximityStrategy;
    @Autowired private SkillLevelMatching skillLevelStrategy;


    public  Match convertToEntity(MatchDTO matchDTO) {
        Match match = new Match();
        List<Participant> players = new ArrayList<>();
        List <User> users=null; 

        Sport aux = sportService.getSportById(matchDTO.getSport());

        System.out.println("Converting MatchDTO to Match entity: " + aux.getName());
        try {
            match.setSport(sportService.getSportById(matchDTO.getSport())); // Assuming findByName returns an Optional<Sport>
        } catch (Exception e) {
            // Manejo de error si el ID no es válido
            throw new IllegalArgumentException("Invalid match ID: " + matchDTO);
        }
        
        match.setDuration(matchDTO.getDuration());
        match.setStartTime(matchDTO.getStartTime());
        match.setLocation(locationMapper.convertToLocationEntity(matchDTO.getLocation()));


            // Buscar y asignar jugadores
    
        if (matchDTO.getIdPlayers().length>0) {
            for (int playerId : matchDTO.getIdPlayers()) 
                users.add(userService.getUserById(playerId));
            
            match.addParticipants(users);            
        }    
            match.setMatchingStrategy(StrategyFactory.createStrategy(matchDTO.getStrategy()));  
            
       
        
        return match;
}

    public static MatchDTO convertToDTO(Match match) {

        MatchDTO matchDTO = new MatchDTO(match.getSport().getId(), match.getDuration(), match.getStartTime()); 
        matchDTO.setIdPlayers(match.getParticipants().stream()
                .mapToInt(participant -> participant.getUser().getId())
                .toArray());
        matchDTO.setStrategy(match.getMatchingStrategy() != null ? match.getMatchingStrategy().getName() : null);

        
        return matchDTO;
    }



}
