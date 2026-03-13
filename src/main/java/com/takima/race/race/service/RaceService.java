package com.takima.race.race.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.takima.race.race.entities.Race;
import com.takima.race.race.repesitories.RaceRepository;
import com.takima.race.registration.repositories.registrationRepository;


@Service
public class RaceService {
    private final RaceRepository raceRepository; 
    private final registrationRepository registrationRepository;

    public RaceService(RaceRepository raceRepository,  registrationRepository registrationRepository) {
        this.raceRepository = raceRepository;
        this.registrationRepository = registrationRepository; 
    }

    public List<Race> getAll(){
        return this.raceRepository.findAll();
    }

    public Race getById(Long id){
        return this.raceRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        String.format("Race is not found", id)
                )
        );
    }
    public Race create(Race race){
        return this.raceRepository.save(race); 
    }


    public Long getParticipantsCount(Long raceId){
        this.getById(raceId); //verifie si la course existe 
        return registrationRepository.countByRaceId(raceId);
}

}


