package com.takima.race.race.entities;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Race {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; 
    private String name; 
    private LocalDate date; 
    private String location; 
    private int maxParticipants;

    public Race() {
    }

    public Race(LocalDate date, Long id, String location, int maxParticipants, String name) {
        this.date = date;
        this.id = id;
        this.location = location;
        this.maxParticipants = maxParticipants;
        this.name = name;
    }





    ///getters et setters 

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public LocalDate getDate() {
        return date;
    }
    public void setDate(LocalDate date) {
        this.date = date;
    }
    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }
    public int getMaxParticipants() {
        return maxParticipants;
    }
    public void setMaxParticipants(int maxParticipants) {
        this.maxParticipants = maxParticipants;
    }

    
}
