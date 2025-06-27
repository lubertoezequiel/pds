package com.g4.tp.mapper;

import com.g4.tp.DTOs.SportDTO;
import com.g4.tp.model.entities.Sport;
import org.springframework.stereotype.Component;

@Component
public class SportMapper {
    public SportDTO convertToDTO(Sport sport) {
        SportDTO sportDTO = new SportDTO(sport.getName(), sport.getDescription(), sport.getRequiredPlayers());
        sportDTO.setId(sport.getId());
        return sportDTO;
    }

    public Sport convertToEntity(SportDTO sportDTO) {
        Sport sport = new Sport(sportDTO.getName(), sportDTO.getDescription(), sportDTO.getRequiredPlayers());
        return sport;
    }

}
