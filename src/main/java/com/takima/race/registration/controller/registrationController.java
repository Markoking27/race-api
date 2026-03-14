package com.takima.race.registration.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/registration")
public class RegistrationController {

    private Long runnerId ; 

        // Constructeur vide 
    public RegistrationController() {}

    public Long getRunnerId() {
        return runnerId;
    }

    public void setRunnerId(Long runnerId) {
        this.runnerId = runnerId;
    }


}
