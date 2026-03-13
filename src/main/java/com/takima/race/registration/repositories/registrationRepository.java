package com.takima.race.registration.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.takima.race.registration.entities.registration;

public interface registrationRepository extends JpaRepository<registration, Long> {
    long countByRaceId(Long raceId);

}
