
package com.g4.tp.model.strategy;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.g4.tp.model.entities.Match;
import com.g4.tp.model.entities.User;

public class ProximityMatching implements IMatchingStrategy {

    private double maxDistance;

    public ProximityMatching(double maxDistance) {
        this.maxDistance = maxDistance;
    }

    @Override
    public List<User> matchPlayers(List<User> availableUsers, Match match) {
        if (availableUsers == null || availableUsers.isEmpty()) {
            return new ArrayList<>();
        }

        List<User> filteredUsers = new ArrayList<>();
        
        for (User user : availableUsers) {
            double distance = calculateDistance(user, match);
            if (distance <= maxDistance) {
                filteredUsers.add(user);
            }
        }

        // Ordenar por distancia (más cercanos primero)
        filteredUsers.sort((u1, u2) -> {
            double dist1 = calculateDistance(u1, match);
            double dist2 = calculateDistance(u2, match);
            return Double.compare(dist1, dist2);
        });

        // Retornar solo los jugadores necesarios para completar el partido
        int playersNeeded = match.getSport().getRequiredPlayers() - match.getPlayers().size();
        return filteredUsers.stream()
                .limit(playersNeeded)
                .collect(Collectors.toList());
    }

    private double calculateDistance(User user, Match match) {
        // Usar la fórmula de Haversine para calcular distancia entre coordenadas
        double lat1 = Math.toRadians(user.getLocation().getLatitude());
        double lon1 = Math.toRadians(user.getLocation().getLongitude());
        double lat2 = Math.toRadians(match.getLocation().getLongitude());
        double lon2 = Math.toRadians(match.getLocation().getLongitude());

        double dlat = lat2 - lat1;
        double dlon = lon2 - lon1;

        double a = Math.sin(dlat/2) * Math.sin(dlat/2) + 
                  Math.cos(lat1) * Math.cos(lat2) * 
                  Math.sin(dlon/2) * Math.sin(dlon/2);
        
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        
        // Radio de la Tierra en kilómetros
        double earthRadius = 6371.0;
        
        return earthRadius * c;
    }

    // Getters and Setters
    public double getMaxDistance() {
        return maxDistance;
    }

    public void setMaxDistance(double maxDistance) {
        this.maxDistance = maxDistance;
    }
}