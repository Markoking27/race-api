package com.takima.race.race.service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.takima.race.race.entities.Race;
import com.takima.race.race.repositories.RaceRepository;
import com.takima.race.registration.entities.Registration;
import com.takima.race.registration.repositories.RegistrationRepository;
import com.takima.race.runner.entities.Runner;
import com.takima.race.runner.repositories.RunnerRepository;


@Service
public class RaceService {
    private final RaceRepository raceRepository; 
    private final RegistrationRepository registrationRepository;
    private final RunnerRepository runnerRepository;

    public RaceService(RaceRepository raceRepository,  RegistrationRepository registrationRepository, RunnerRepository runnerRepository) {
        this.raceRepository = raceRepository;
        this.registrationRepository = registrationRepository; 
        this.runnerRepository = runnerRepository; 
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

    public Registration createRegistration(Long raceId, Long runnerId){
        Race race = raceRepository.findById(raceId)
            .orElseThrow(() -> new RuntimeException("Race not found"));
        // verifie si la course existe

        Runner runner = runnerRepository.findById(runnerId)
            .orElseThrow(() -> new RuntimeException("Runner not found"));
        // verifie si le runner existe

        Registration registration = new Registration(race, LocalDate.now(), runner); 

        return this.registrationRepository.save(registration);
        
    }

    public List<Runner> RunnerOfRace(Long RaceId){
        List <Registration> registration = registrationRepository.findByRaceId(RaceId);
        // liste de toutes inscriptions a la course

        return registration.stream()
                        .map(Registration::getRunner)
                        .collect(Collectors.toList());
        // recupere la liste des runners de inscriptions 

    }

    public Race UpdateRace(Long raceId, Race race){

        /// recuperer et verifier si la course que l'on veut modifier existe 
        Race existRace = raceRepository.findById(raceId).orElseThrow(() ->
                new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        String.format("Race is not found", raceId)
                )
        );

        // modifier les champs de l'ancienne par ceux présent dans le body
        existRace.setName(race.getName());
        existRace.setDate(race.getDate()); 
        existRace.setLocation(race.getLocation());
        existRace.setMaxParticipants(race.getMaxParticipants());

         // Sauvegarder les modifications
        return raceRepository.save(existRace);
    }

}


