package com.g4.tp.mapper;

import com.g4.tp.DTOs.LocationDTO;
import com.g4.tp.model.entities.Location;
import org.springframework.stereotype.Component;

@Component
public class LocationMapper {
    public LocationDTO convertToLocationDTO(Location location) {
        LocationDTO locationDTO = new LocationDTO();

        locationDTO.setLatitude(location.getLatitude());
        locationDTO.setLongitude(location.getLongitude());
        locationDTO.setAddress(location.getAddress()); // Assuming Location has an address field

        return locationDTO;
    }
    public  Location convertToLocationEntity(LocationDTO locationDTO) {
        Location location = new Location();

        location.setLatitude(locationDTO.getLatitude());
        location.setLongitude(locationDTO.getLongitude());
        location.setAddress(locationDTO.getAddress()); // Assuming Location has an address field

        return location;
    }

}
