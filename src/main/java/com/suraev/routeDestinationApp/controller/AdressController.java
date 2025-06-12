package com.suraev.routeDestinationApp.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.suraev.routeDestinationApp.service.DadataService;
import com.suraev.routeDestinationApp.dto.DadataRequest;
import com.suraev.routeDestinationApp.dto.DadataResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import java.util.List;




@RestController
@RequestMapping("/getDistance")
@RequiredArgsConstructor
public class AdressController {

    private final DadataService dadataServiceImpl;

    @PostMapping
    // TO
    public ResponseEntity<List<DadataResponse>> getDistance(@RequestBody String [] adresses) {

       
        List<DadataResponse> ddataResponse = dadataServiceImpl.getCoordinate(adresses);

        return ResponseEntity.ok(ddataResponse);
}
}
