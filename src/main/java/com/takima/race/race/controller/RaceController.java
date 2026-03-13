package com.takima.race.race.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.takima.race.race.entities.Race;
import com.takima.race.race.service.RaceService;






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


}
