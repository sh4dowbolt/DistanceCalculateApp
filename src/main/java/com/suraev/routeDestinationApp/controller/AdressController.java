package com.suraev.routeDestinationApp.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.suraev.routeDestinationApp.service.DadataService;
import com.suraev.routeDestinationApp.dto.DadataResponse;
import com.suraev.routeDestinationApp.dto.ExceptionResponse;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import java.util.List;
import com.suraev.routeDestinationApp.dto.BadRequestException;


@RestController
@RequestMapping("/getDistance")
@RequiredArgsConstructor
public class AdressController {

    private final DadataService dadataServiceImpl;

    @PostMapping
    // TODO: check adresses is UTF-8 with annotation
    public ResponseEntity<DadataResponse> getDistance(@RequestBody String [] adress) throws BadRequestException, ExceptionResponse {
        if(adress.length>1) {
            throw new BadRequestException("Too many addresses, only one address is allowed", "/getDistance");
        }

    //TODO: call the calculateDistance method   
        DadataResponse ddataResponse = dadataServiceImpl.getCoordinate(adress);

        return ResponseEntity.ok(ddataResponse);
}
}
