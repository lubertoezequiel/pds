package com.g4.tp.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.g4.tp.model.entities.Location;
import com.g4.tp.model.entities.Match;
import com.g4.tp.model.entities.PARTICIPATIONSTATUS;
import com.g4.tp.model.entities.Participant;
import com.g4.tp.model.entities.Sport;
import com.g4.tp.model.entities.User;
import com.g4.tp.model.state.MatchContext;
import com.g4.tp.model.state.MatchStateEnum;
import com.g4.tp.model.state.StateFactory;
import com.g4.tp.repository.IMatchRepository;

@Service
public class MatchService implements IMatchService {

    @Autowired
    private IMatchRepository matchRepository;
    @Autowired
    private ISportService sportService;
    @Autowired
    private IUserService userService;

    @Override
    public Match createMatch(Match match) {

        if (match == null) {
            throw new IllegalArgumentException("Match cannot be null");
        }

        if (match.getSport() == null) {
            throw new IllegalArgumentException("Match must have a sport associated");
        }

        if (match.getLocation() == null) {
            throw new IllegalArgumentException("Match must have a location associated");
        }

        Sport sport = sportService.getSportById(match.getSport().getId());

        if (match.getParticipants().size() > sport.getRequiredPlayers()) {
            throw new IllegalArgumentException("Quantity of players error ");
        }
        List<User> usuarios = userService.findAll();
        match.add(usuarios);
        
        MatchContext matchContext = new MatchContext(match);
        matchContext.needPlayer();

        Match createdMatch = matchRepository.save(match); // el ID ya se setea en el objeto
        System.out.println("Match created with ID: " + createdMatch.getId());
        return createdMatch;

    }

    @Override
    public Match getMatchById(int id) {
        Match match = matchRepository.findById(id).orElse(null);

        if (match == null) {
            throw new IllegalArgumentException("Match not found with ID: " + id);
        }
        return match;

    }

    @Override
    public Match joinMatch(int userId, int matchId) {
        Match match = matchRepository.findById(matchId)
                .orElseThrow(() -> new IllegalArgumentException("Match not found with ID: " + matchId));

        User user = userService.getUserById(userId);
        //Valido que el usuario ya no este agregado al partido
        if (match.getParticipants().stream()
                .anyMatch(participant -> participant.getUser().getId() == userId)) {
            throw new IllegalArgumentException("User already joined the match.");
        }
        MatchContext matchContext = new MatchContext(match);
        matchContext.joinUser(user); // Cambia el estado del partido a "Necesitamos jugadores"
        return matchRepository.save(match); // Guardar el estado actualizado del partido
        // Validación de máximo jugadores (opcional)

    }

    @Override
    public Match cancelMatch(int matchId) {
        Match match = matchRepository.findById(matchId)
                .orElseThrow(() -> new IllegalArgumentException("Match not found with ID: " + matchId));

        MatchContext matchContext = new MatchContext(match);
        matchContext.cancel();

        return matchRepository.save(matchContext.getMatch()); // Guardar el estado actualizado del partido
    }

    @Override
    public List<Match> getMatchesByProximityByUserId(int userId, double radius) {
        User user = userService.getUserById(userId);
        if (user == null || user.getLocation() == null) {
            throw new IllegalArgumentException("User not found or user location is null");
        }

        return getMatchesByProximity(user.getLocation(), radius);

    }

    @Override
    public List<Match> getMatchesByProximity(Location userLocation, double radius) {
        if (userLocation == null) {
            throw new IllegalArgumentException("User location cannot be null");
        }

        if (radius <= 0) {
            throw new IllegalArgumentException("Radius must be greater than zero");
        }

        List<Match> allMatches = matchRepository.findAll();
        return allMatches.stream()
                .filter(match -> match.getLocation() != null
                && match.getLocation().isWithinRadius(userLocation, radius))
                .collect(Collectors.toList());

    }

    @Override
    public Match acceptParticipation(int matchId, int userId) {
        Match match = matchRepository.findById(matchId)
                .orElseThrow(() -> new IllegalArgumentException("Match not found with ID: " + matchId));

        User user = userService.getUserById(userId);
        if (user == null) {
            throw new IllegalArgumentException("User not found with ID: " + userId);
        }

        // Check if the user is already a participant
        List<Participant> participant = match.getParticipants();

        if (participant == null) {
            throw new IllegalArgumentException("No participants found for this match.");
        }
        for (Participant p : participant) {
            if (p.getUser().getId() == userId) {
                if (p.getStatus() == PARTICIPATIONSTATUS.PENDING) {
                    p.setStatus(PARTICIPATIONSTATUS.ACCEPTED);
                } else {
                    throw new IllegalArgumentException("User already accepted participation in this match.");
                }

            }
        }

        MatchContext matchContext = new MatchContext(match);
        matchContext.confirmMatch();

        return matchRepository.save(match);
    }

    @Scheduled(fixedRate = 60000) // cada 60 segundos
    public void updateMatchStates() {

        List<MatchStateEnum> estados = List.of(
                MatchStateEnum.NEED_PLAYER,
                MatchStateEnum.MATCH_ARRANGED,
                MatchStateEnum.CONFIRMED,
                MatchStateEnum.IN_PROGRESS
        );

        List<Match> matches = matchRepository.findAllByStateEnumIn(estados);

        for (Match match : matches) {
            MatchContext context = new MatchContext(match);
            context.setCurrentState(StateFactory.createState(match.getStateEnum()));
            context.updateProgress();
            matchRepository.save(context.getMatch());
        }

    }
}
