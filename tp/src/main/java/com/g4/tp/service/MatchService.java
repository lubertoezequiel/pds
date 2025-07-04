package com.g4.tp.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.g4.tp.model.entities.Location;
import com.g4.tp.model.entities.Match;
import com.g4.tp.model.entities.PARTICIPATIONSTATUS;
import com.g4.tp.model.entities.Participant;
import com.g4.tp.model.entities.SKILL_LEVEL_ENUM;
import com.g4.tp.model.entities.Sport;
import com.g4.tp.model.entities.User;
import com.g4.tp.model.state.CancelledState;
import com.g4.tp.model.state.FinishedState;
import com.g4.tp.model.state.IMatchState;
import com.g4.tp.model.state.InProgressState;
import com.g4.tp.model.state.MatchContext;
import com.g4.tp.model.state.MatchStateEnum;
import com.g4.tp.model.strategy.IMatchingStrategy;
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
        
        if (match.getParticipants() == null || match.getParticipants().isEmpty() || match.getParticipants().size() > sport.getRequiredPlayers()) {
            throw new IllegalArgumentException("Quantity of players error ");
        } 
        
        Match createdMatch =matchRepository.save(match); // el ID ya se setea en el objeto
        System.out.println("Match created with ID: " + createdMatch.getId() );
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
    public void updateMatch(int id, Match match) {

    }

    @Override
    public void deleteMatch(int id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteMatch'");
    }

    @Override
    public List<Match> getAllMatches() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllMatches'");
    }

    @Override
    public List<Match> getMatchesByUser(User user) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getMatchesByUser'");
    }

    @Override
    public List<Match> getMatchesBySport(Sport sport) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getMatchesBySport'");
    }

    @Override
    public List<Match> getMatchesByLocation(Location location) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getMatchesByLocation'");
    }

    @Override
    public List<Match> getMatchesByDate(LocalDateTime date) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getMatchesByDate'");
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
    public void confirmMatch(int matchId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'confirmMatch'");
    }

    @Override
    public void finishMatch(int matchId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'finishMatch'");
    }


    @Override
    public List<User> matchPlayers(List<User> availableUsers, Match match) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'matchPlayers'");
    }

    @Override
    public void setMatchingStrategy(IMatchingStrategy strategy) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setMatchingStrategy'");
    }

    @Override
    public IMatchingStrategy getMatchingStrategy() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getMatchingStrategy'");
    }

    @Override
    public void setMatchState(IMatchState state) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setMatchState'");
    }

    @Override
    public IMatchState getMatchState(Match match) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getMatchState'");
    }

    @Override
    public void changeMatchState(Match match, IMatchState newState) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'changeMatchState'");
    }

    @Override
    public List<Match> getMatchesBySkillLevel(SKILL_LEVEL_ENUM minSkillLevel,
            SKILL_LEVEL_ENUM maxSkillLevel) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getMatchesBySkillLevel'");
    }

    @Override
    public List<Match> getMatchesBySportAndSkillLevel(Sport sport, SKILL_LEVEL_ENUM minSkillLevel,
            SKILL_LEVEL_ENUM maxSkillLevel) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getMatchesBySportAndSkillLevel'");
    }

    @Override
    public List<Match> getMatchesBySportAndLocation(Sport sport, Location location) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getMatchesBySportAndLocation'");
    }
    // Business logic related to Match

    @Override
    public void updateMatchProgress(Long matchId, int progress) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateMatchProgress'");
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
                .filter(match -> match.getLocation() != null && 
                                 match.getLocation().isWithinRadius(userLocation, radius))
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
        List <Participant> participant = match.getParticipants();

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
        LocalDateTime now = LocalDateTime.now();
        List<MatchStateEnum> estados = List.of(
        MatchStateEnum.NEED_PLAYER,
        MatchStateEnum.MATCH_ARRANGED
        );

        List<Match> matchesToCancel = matchRepository.findAllByStartTimeBeforeAndStatus(now, estados);


        for (Match match : matchesToCancel) {
            MatchContext context = new MatchContext(match);
            context.setCurrentState(new CancelledState());
            matchRepository.save(context.getMatch());
        }

        List<Match> matchesToStart = matchRepository.findAllByStartTimeBeforeAndStatus(now, MatchStateEnum.CONFIRMED);

        for (Match match : matchesToStart) {
            MatchContext context = new MatchContext(match);
            context.setCurrentState(new InProgressState());
            matchRepository.save(context.getMatch());
        }

        List<Match> matchesToFinish = matchRepository.findAllByStartTimeBeforeAndStatus(now, MatchStateEnum.IN_PROGRESS);

        for (Match match : matchesToFinish) {
            MatchContext context = new MatchContext(match);
            context.setCurrentState(new FinishedState()); // Cambiar a estado finalizado o el que corresponda
            matchRepository.save(context.getMatch());
        }
    }
}
