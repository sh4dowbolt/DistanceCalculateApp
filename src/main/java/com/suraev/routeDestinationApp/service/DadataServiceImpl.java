package com.suraev.routeDestinationApp.service;

import org.springframework.stereotype.Service;
import com.suraev.routeDestinationApp.dto.DadataResponse;
import com.suraev.routeDestinationApp.exception.BadRequestException;
import com.suraev.routeDestinationApp.exception.ExceptionResponse;
import org.springframework.web.client.RestClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.HttpStatus;
import com.suraev.routeDestinationApp.util.ExceptionResponseHandler;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.suraev.routeDestinationApp.dto.CoordinateDTO;

@Service
public class DadataServiceImpl implements DadataService {
    private final RestClient restClient;
    private final String ddataUrl;
    private final String apiKey;
    private final String secretKey;
    private final ObjectMapper objectMapper;
    private  final String PATH_URL;

    public DadataServiceImpl(
        RestClient restClient,
        @Value("${dadata.url}") String ddataUrl, 
        @Value("${dadata.apiKey}") String apiKey, 
        @Value("${dadata.secretKey}") String secretKey,
        @Value("${getDistance.path}") String PATH_URL
    ) {
        this.restClient = restClient;
        this.ddataUrl = ddataUrl;
        this.apiKey = apiKey;
        this.secretKey = secretKey;
        this.objectMapper = new ObjectMapper();
        this.PATH_URL = PATH_URL;
    }

        @Override
        public CoordinateDTO getCoordinate(String [] adress) throws ExceptionResponse, BadRequestException {

        DadataResponse [] coordinate = restClient.post()
        .uri(ddataUrl)
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON)
        .body(adress)
        .header("Authorization", "Token " + apiKey)
        .header("X-Secret", secretKey)
        .retrieve()
        .onStatus(status -> status.equals(HttpStatus.BAD_REQUEST), (req, res) -> ExceptionResponseHandler.handleStatus(res, objectMapper))
        .onStatus(status -> status.value() == 401, (req, res) -> ExceptionResponseHandler.handleStatus(res, objectMapper))
        .onStatus(status -> status.value() == 403, (req, res) -> ExceptionResponseHandler.handleStatus(res, objectMapper))
        .onStatus(status -> status.value() == 429, (req, res) -> ExceptionResponseHandler.handleStatus(res, objectMapper))
        .onStatus(status -> status.equals(HttpStatus.INTERNAL_SERVER_ERROR), (req, res) -> ExceptionResponseHandler.handleStatus(res, objectMapper))
        .body(DadataResponse[].class);
        
        if(coordinate == null || coordinate.length == 0) {
            throw new BadRequestException("No coordinates found in response", PATH_URL);
        }
        
        CoordinateDTO coordinateDTO = new CoordinateDTO();
        coordinateDTO.setLatitude(coordinate[0].getLatitude());
        coordinateDTO.setLongitude(coordinate[0].getLongitude());
        return coordinateDTO;
    }

}