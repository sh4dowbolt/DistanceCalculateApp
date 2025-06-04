package com.suraev.routeDestinationApp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/getDiff")
public class AdressController {

    @PostMapping("")
    public String getAdress(@RequestBody String adress) {

        
        return "Hello World";
    }
}
