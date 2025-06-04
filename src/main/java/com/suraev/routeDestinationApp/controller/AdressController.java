package com.suraev.routeDestinationApp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/adress")
public class AdressController {

    @GetMapping()
    public String getAdress() {
        return "Hello World";
    }
}
