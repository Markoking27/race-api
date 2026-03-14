package com.takima.race.race.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.takima.race.race.entities.Race;
import com.takima.race.race.service.RaceService;
import com.takima.race.registration.controller.RegistrationController;
import com.takima.race.registration.entities.Registration;
import com.takima.race.runner.entities.Runner;






@RestController
@RequestMapping("/races")
public class RaceController {
    private final RaceService raceService; 

    public RaceController(RaceService raceService) {
        this.raceService = raceService;
    }

    @GetMapping
    public List<Race> getAll() {
        return raceService.getAll();
    }

    @GetMapping("/{id}")
    public Race getById(@PathVariable Long id) {
        return raceService.getById(id); 
    }

    @PostMapping
    public Race create(
        @RequestBody Race race
    ) {
        return this.raceService.create(race);
    }

    @GetMapping("/{raceId}/participants/count")
    public String getParticipantsCount(@PathVariable Long raceId){

        Long count = raceService.getParticipantsCount(raceId);

        return "count : "+count ;
    }

    @PostMapping("/{raceId}/registrations")
    public Registration create(@PathVariable Long raceId,@RequestBody RegistrationController rc){
        return this.raceService.createRegistration(raceId, rc.getRunnerId()) ; 

    }

    @GetMapping("/{raceId}/registrations")
    public List<Runner> RunnerOfRace(@PathVariable Long raceId){
        return this.raceService.RunnerOfRace(raceId) ; 
    }

    @PutMapping("/{raceId}")
    public Race UpdateRace(@PathVariable Long raceId, @RequestBody Race race){
        return this.raceService.UpdateRace(raceId, race); 
    }



}
