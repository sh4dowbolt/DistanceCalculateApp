package com.suraev.routeDestinationApp.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.suraev.routeDestinationApp.dto.ExceptionResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import com.suraev.routeDestinationApp.dto.BadRequestException;
import com.suraev.routeDestinationApp.service.CalculationServiceImpl;
import com.suraev.routeDestinationApp.dto.CoordinatePosResponse;
import com.suraev.routeDestinationApp.dto.MeasureType;
import org.springframework.web.bind.annotation.RequestParam;
import jakarta.validation.constraints.NotNull;


@RestController
@RequestMapping("/getDistance")
public class AdressController {

    private final String PATH_URL;  
    private final CalculationServiceImpl calculationServiceImpl;

    public AdressController(CalculationServiceImpl calculationServiceImpl,
     @Value("${getDistance.path}") String pathUrl) {
        this.calculationServiceImpl = calculationServiceImpl;
        this.PATH_URL = pathUrl;
    }   

    @PostMapping
    public ResponseEntity<CoordinatePosResponse> getDistance(@NotNull @RequestBody String [] adress,
                                 @RequestParam(name = "measureType", defaultValue = "km") MeasureType measureType) throws BadRequestException, ExceptionResponse {
        if(adress.length>1) {
            throw new BadRequestException("Too many addresses, only one address is allowed", PATH_URL);
        }

        CoordinatePosResponse coordinatePosResponse = calculationServiceImpl.calculateDistance(adress, measureType);
        return ResponseEntity.ok(coordinatePosResponse);
}
}
