package com.g4.tp.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.g4.tp.DTOs.MatchDTO;
import com.g4.tp.model.entities.Match;
import com.g4.tp.model.entities.Sport;
import com.g4.tp.model.entities.User;
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



    public  Match convertToEntity(MatchDTO matchDTO) {
        Match match = new Match();
        List<User> players = new ArrayList<>();

        Sport aux = sportService.getSportById(2);

        System.out.println("Converting MatchDTO to Match entity: " + aux.getName());
        try {
            match.setSport(sportService.getSportById(matchDTO.getSport())); // Assuming findByName returns an Optional<Sport>
        } catch (Exception e) {
            // Manejo de error si el ID no es válido
            throw new IllegalArgumentException("Invalid match ID: " + matchDTO);
        }
        
        match.setDuration(matchDTO.getDuration());
        match.setDate(matchDTO.getDate());
        match.setTime(matchDTO.getTime());
        match.setLocation(locationMapper.convertToLocationEntity(matchDTO.getLocation()));

            // Buscar y asignar jugadores
    
        if (matchDTO.getIdPlayers() != null) {
            for (int playerId : matchDTO.getIdPlayers()) {
                User user = userService.getUserById(playerId);
                if (user != null) {
                players.add(user);
            } else {
                throw new IllegalArgumentException("User not found with ID: " + playerId);
            }
        }
    }
    match.setPlayers(players);
       
        return match;
    }


    public static MatchDTO convertToDTO(Match match) {

        MatchDTO matchDTO = new MatchDTO(match.getSport().getId(), match.getDuration(), match.getDate(), match.getTime());
        matchDTO.setId(match.getId());
        matchDTO.setDuration(match.getDuration());
        matchDTO.setDate(match.getDate());
        matchDTO.setTime(match.getTime());
        matchDTO.setIdPlayers(match.getPlayers().stream().mapToInt(User::getId).toArray());

        
        return matchDTO;
    }



}
