package com.suraev.routeDestinationApp.service;

import org.springframework.stereotype.Service;

import com.suraev.routeDestinationApp.dto.DadataRequest;
import com.suraev.routeDestinationApp.dto.DadataResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.client.RestClient;
import java.util.Arrays;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.HttpStatus;
import java.util.List;
import com.suraev.routeDestinationApp.util.HttpStatusHandler;
import com.fasterxml.jackson.databind.ObjectMapper;


@Service
public class DadataServiceImpl implements DadataService {
    private final RestClient restClient;
    private final String ddataUrl;
    private final String apiKey;
    private final String secretKey;
    private final ObjectMapper objectMapper;

    public DadataServiceImpl(
        RestClient restClient,
        @Value("${dadata.url}") String ddataUrl, 
        @Value("${dadata.apiKey}") String apiKey, 
        @Value("${dadata.secretKey}") String secretKey
    ) {
        this.restClient = restClient;
        this.ddataUrl = ddataUrl;
        this.apiKey = apiKey;
        this.secretKey = secretKey;
        this.objectMapper = new ObjectMapper();
    }

    @Override
    public List<DadataResponse> getCoordinate(String[] adresses) {

        // TODO: check parameters is UTF-8
      
        DadataResponse[] responseAll = restClient.post()
        .uri(ddataUrl)
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON)
        .body(adresses)
        .header("Authorization", "Token " + apiKey)
        .header("X-Secret", secretKey)
        .retrieve()
        .onStatus(statusCode -> statusCode.equals(HttpStatus.BAD_REQUEST), (req, response) -> {
            HttpStatusHandler.handleStatus(response, objectMapper);
        })
        .onStatus(statusCode -> statusCode.equals(HttpStatus.valueOf(401)), (req, response) -> {
            HttpStatusHandler.handleStatus(response, objectMapper);
        })
        .onStatus(statusCode -> statusCode.equals(HttpStatus.valueOf(403)), (req, response) -> {
            HttpStatusHandler.handleStatus(response, objectMapper);
        })
        .onStatus(statusCode -> statusCode.equals(HttpStatus.valueOf(405)), (req, response) -> {
            HttpStatusHandler.handleStatus(response, objectMapper);
        })
        .onStatus(statusCode -> statusCode.equals(HttpStatus.valueOf(429)), (req, response) -> {
            HttpStatusHandler.handleStatus(response, objectMapper);
        })
        .onStatus(statusCode -> statusCode.equals(HttpStatus.valueOf(500)), (req, response) -> {
            HttpStatusHandler.handleStatus(response, objectMapper);
        })
        .body(DadataResponse[].class);

        return Arrays.asList(responseAll);
    }

}