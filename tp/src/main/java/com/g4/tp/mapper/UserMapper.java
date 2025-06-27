package com.g4.tp.mapper;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.g4.tp.DTOs.UserDTO;
import com.g4.tp.model.entities.Location;
import com.g4.tp.model.entities.SKILL_LEVEL_ENUM;
import com.g4.tp.model.entities.Sport;
import com.g4.tp.model.entities.User;
import com.g4.tp.service.ISportService;

@Component
public class UserMapper {
    
    @Autowired
    private ISportService sportService;

    public UserDTO convertToDTO(User user) {
    UserDTO dto = new UserDTO();

    dto.setName(user.getName());
    dto.setEmail(user.getEmail());
    dto.setPassword(user.getPassword());
    dto.setId(user.getId());
    dto.setLatitude(user.getLocation().getLatitude()); 
    dto.setLongitude(user.getLocation().getLongitude());
    dto.setAddress(user.getLocation().getAddress()); 
    

    // Convertir lista de Sports a array de IDs
    if (user.getPracticedSports() != null) {
        int[] ids = user.getPracticedSports().stream()
            .mapToInt(sport -> sport.getId())
            .toArray();
        dto.setPracticedSportIds(ids);
    }

    // Deporte favorito
    if (user.getFavoriteSport() != null) {
        dto.setFavoriteSportId(user.getFavoriteSport().getId());
    }

    // Skill level
    if (user.getSkillLevel() != null) {
        dto.setSkillLevel(user.getSkillLevel().toString());
    }

    return dto;
}


    public User convertToEntity(UserDTO dto) {

        List<Sport> practicedSports = Arrays.stream(dto.getPracticedSportIds())
        .mapToObj(id -> sportService.getSportById(id))
        .collect(Collectors.toList());

        if (practicedSports.isEmpty()) {
            throw new IllegalArgumentException("At least one practiced sport must be provided");
        }
        Sport favoriteSport = sportService.getSportById(dto.getFavoriteSportId());

        SKILL_LEVEL_ENUM skillLevel = SKILL_LEVEL_ENUM.valueOf(dto.getSkillLevel().toUpperCase());
        Location location = new Location();
        location.setLatitude(dto.getLatitude());
        location.setLongitude(dto.getLongitude());
        location.setAddress(dto.getAddress()); // Set the address from DTO

        User user = new User();
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        user.setPracticedSports(practicedSports);
        user.setFavoriteSport(favoriteSport);
        user.setSkillLevel(skillLevel);

        user.setLocation(location); // Set the location from DTO

        return user;


    }

}
