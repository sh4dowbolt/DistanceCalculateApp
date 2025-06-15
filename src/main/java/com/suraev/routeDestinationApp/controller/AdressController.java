package com.suraev.routeDestinationApp.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.suraev.routeDestinationApp.dto.ExceptionResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import com.suraev.routeDestinationApp.dto.BadRequestException;
import com.suraev.routeDestinationApp.service.CalculationServiceImpl;
import com.suraev.routeDestinationApp.dto.CoordinatePosResponse;


@RestController
@RequestMapping("/getDistance")
@RequiredArgsConstructor
public class AdressController {

    private final CalculationServiceImpl calculationServiceImpl;
    @PostMapping
    // TODO: check adresses is UTF-8 with annotation
    public ResponseEntity<CoordinatePosResponse> getDistance(@RequestBody String [] adress) throws BadRequestException, ExceptionResponse {
        if(adress.length>1) {
            throw new BadRequestException("Too many addresses, only one address is allowed", "/getDistance");
        }
        CoordinatePosResponse coordinatePosResponse = calculationServiceImpl.calculateDistance(adress);
        return ResponseEntity.ok(coordinatePosResponse);
}
}
