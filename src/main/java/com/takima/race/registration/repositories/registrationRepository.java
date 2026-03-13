package com.takima.race.registration.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.takima.race.registration.entities.Registration;

@Repository
public interface RegistrationRepository extends JpaRepository<Registration, Long> {
    Long countByRaceId(Long raceId);

}
