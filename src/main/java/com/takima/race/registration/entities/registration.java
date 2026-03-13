package com.takima.race.registration.entities;

import java.time.LocalDate;

import com.takima.race.race.entities.Race;
import com.takima.race.runner.entities.Runner;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Registration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "runner_id")
    private Runner runner;

    @ManyToOne
    @JoinColumn(name = "race_id")
    private Race race;

    @JoinColumn(name = "registration_date")
    private LocalDate registrationDate;

    //constructeurs 

    public Registration(Race race, LocalDate registrationDate, Runner runner) {
        this.race = race;
        this.registrationDate = registrationDate;
        this.runner = runner;
    }



    // getters & setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Runner getRunner() {
        return runner;
    }

    public void setRunner(Runner runner) {
        this.runner = runner;
    }

    public Race getRace() {
        return race;
    }

    public void setRace(Race race) {
        this.race = race;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
    }
}
