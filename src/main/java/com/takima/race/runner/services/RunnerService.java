package com.takima.race.runner.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.takima.race.race.entities.Race;
import com.takima.race.registration.entities.Registration;
import com.takima.race.registration.repositories.RegistrationRepository;
import com.takima.race.runner.entities.Runner;
import com.takima.race.runner.repositories.RunnerRepository;

@Service
public class RunnerService {

    private final RunnerRepository runnerRepository;
    private final RegistrationRepository registrationRepository; 

    public RunnerService(RunnerRepository runnerRepository,RegistrationRepository registrationRepository) {
        this.runnerRepository = runnerRepository;
        this.registrationRepository = registrationRepository;
    }

    public List<Runner> getAll() {
        return runnerRepository.findAll();
    }

    public Runner getById(Long id) {
        return runnerRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        String.format("Runner %s not found", id)
                )
        );
    }

    public Runner create(Runner runner){

    if (runner.getEmail() == null || !runner.getEmail().contains("@")) {
        throw new ResponseStatusException(
            HttpStatus.BAD_REQUEST,
            "Email must contain @"
        );
    }

    return runnerRepository.save(runner);
}

    public List<Race> RaceofRunner(Long runnerId){
        List <Registration> registration = registrationRepository.findByRunnerId(runnerId);
        // liste de toutes inscriptions a la course

        return registration.stream()
                        .map(Registration::getRace)
                        .collect(Collectors.toList());
        // recupere la liste des runners de inscriptions 
    }

    public void delete(Long id){
        Runner runner = runnerRepository.findById(id).orElseThrow(() ->
            new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                String.format("Runner %s not found", id)
            )
        );
        runnerRepository.delete(runner);
    }

    public Runner update(Long id, Runner runnerData) {

    //Validation course 
    Runner runner = runnerRepository.findById(id).orElseThrow(() ->
                    new ResponseStatusException(
                            HttpStatus.NOT_FOUND,
                            String.format("Runner %s not found", id)
                    )
            );
    
        //Validation email
    if (runnerData.getEmail() == null || !runnerData.getEmail().contains("@")) {
        throw new ResponseStatusException(
            HttpStatus.BAD_REQUEST,
            "Email must contain @"
        );
    }

    runner.setFirstName(runnerData.getFirstName());
    runner.setLastName(runnerData.getLastName());
    runner.setEmail(runnerData.getEmail());
    runner.setAge(runnerData.getAge());

    return runnerRepository.save(runner);
}

}
