package com.g4.tp.model.strategy;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.g4.tp.model.entities.Location;
import com.g4.tp.model.entities.Match;
import com.g4.tp.model.entities.User;

@Component("proximityMatchingStrategy")
public class ProximityMatching implements IMatchingStrategy {

    private double maxDistance = 5.0; // Distancia máxima en kilómetros
    private static final double EARTH_RADIUS_KM = 6371.0;

    @Override
    public String getName() {
        return "Proximity";
    }

    @Override
    public boolean isApplicable(Match match) {
        if (match == null) {
            return false;
        }

        Location matchLocation = match.getLocation();
        return matchLocation != null &&
                matchLocation.getLatitude() != null &&
                matchLocation.getLongitude() != null &&
                isValidCoordinate(matchLocation.getLatitude()) &&
                isValidCoordinate(matchLocation.getLongitude());
    }

    @Override
    public List<User> matchPlayers(List<User> availableUsers, Match match) {
        // Validaciones iniciales
        if (availableUsers == null || availableUsers.isEmpty()) {
            return new ArrayList<>();
        }

        if (!isApplicable(match)) {
            return new ArrayList<>();
        }

        // Calcular cuántos jugadores se necesitan
        int currentParticipants = match.getParticipants() != null ? match.getParticipants().size() : 0;
        int playersNeeded = match.getSport().getRequiredPlayers() - currentParticipants;

        if (playersNeeded <= 0) {
            return new ArrayList<>();
        }

        // Filtrar usuarios válidos por proximidad y disponibilidad
        List<UserWithDistance> usersWithDistance = availableUsers.stream()
                .filter(user -> isUserValidForMatching(user, match))
                .map(user -> new UserWithDistance(user, calculateDistance(user.getLocation(), match.getLocation())))
                .filter(userDist -> userDist.distance <= maxDistance)
                .sorted((u1, u2) -> Double.compare(u1.distance, u2.distance))
                .collect(Collectors.toList());

        // Retornar solo los jugadores necesarios
        return usersWithDistance.stream()
                .limit(playersNeeded)
                .map(userDist -> userDist.user)
                .collect(Collectors.toList());
    }

    /**
     * Valida si un usuario es apto para el emparejamiento
     */
    private boolean isUserValidForMatching(User user, Match match) {
        if (user == null || match == null) {
            return false;
        }

        // Verificar que el usuario tenga ubicación válida
        if (!hasValidLocation(user)) {
            return false;
        }

        // Verificar que el usuario practique el deporte del partido
        if (!isUserAvailableForSport(user, match)) {
            return false;
        }

        // Verificar que el usuario no esté ya en el partido
        if (isUserAlreadyInMatch(user, match)) {
            return false;
        }

        return true;
    }

    /**
     * Verifica si el usuario tiene una ubicación válida
     */
    private boolean hasValidLocation(User user) {
        Location userLocation = user.getLocation();
        return userLocation != null &&
                userLocation.getLatitude() != null &&
                userLocation.getLongitude() != null &&
                isValidCoordinate(userLocation.getLatitude()) &&
                isValidCoordinate(userLocation.getLongitude());
    }

    /**
     * Verifica si el usuario practica el deporte del partido
     */
    private boolean isUserAvailableForSport(User user, Match match) {
        return user.getPracticedSports() != null &&
                user.getPracticedSports().contains(match.getSport());
    }

    /**
     * Verifica si el usuario ya está participando en el partido
     */
    private boolean isUserAlreadyInMatch(User user, Match match) {
        if (match.getParticipants() == null || match.getParticipants().isEmpty()) {
            return false;
        }

        return match.getParticipants().stream()
                .anyMatch(participant -> participant.getUser().equals(user));
    }

    /**
     * Valida si una coordenada es válida
     */
    private boolean isValidCoordinate(Double coordinate) {
        return coordinate != null &&
                coordinate != 0.0 &&
                !coordinate.isNaN() &&
                !coordinate.isInfinite();
    }

    /**
     * Calcula la distancia entre dos ubicaciones usando la fórmula de Haversine
     */
    private double calculateDistance(Location userLocation, Location matchLocation) {
        if (userLocation == null || matchLocation == null) {
            return Double.MAX_VALUE;
        }

        double lat1 = Math.toRadians(userLocation.getLatitude());
        double lon1 = Math.toRadians(userLocation.getLongitude());
        double lat2 = Math.toRadians(matchLocation.getLatitude());
        double lon2 = Math.toRadians(matchLocation.getLongitude());

        double dlat = lat2 - lat1;
        double dlon = lon2 - lon1;

        double a = Math.sin(dlat / 2) * Math.sin(dlat / 2) +
                Math.cos(lat1) * Math.cos(lat2) *
                        Math.sin(dlon / 2) * Math.sin(dlon / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return EARTH_RADIUS_KM * c;
    }

    /**
     * Clase interna para almacenar usuario con su distancia calculada
     */
    private static class UserWithDistance {
        final User user;
        final double distance;

        UserWithDistance(User user, double distance) {
            this.user = user;
            this.distance = distance;
        }
    }

    // Getters and Setters
    public double getMaxDistance() {
        return maxDistance;
    }

    public void setMaxDistance(double maxDistance) {
        if (maxDistance > 0) {
            this.maxDistance = maxDistance;
        } else {
            throw new IllegalArgumentException("La distancia máxima debe ser mayor a 0");
        }
    }

}