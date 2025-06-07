package com.suraev.routeDestinationApp.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/getDistance")
public class AdressController {

    @PostMapping
    public String getDistance(@RequestBody String adress) {

        
        return "Hello World";
    }
}
