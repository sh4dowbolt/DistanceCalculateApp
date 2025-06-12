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


@Service
public class DadataServiceImpl implements DadataService {
    private final RestClient restClient;
    private final String ddataUrl;
    private final String apiKey;
    private final String secretKey;

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
            throw new RuntimeException("Bad Request");
        })
        .onStatus(statusCode -> statusCode.equals(HttpStatus.valueOf(401)), (req, response) -> {
            throw new RuntimeException("Unauthorized");
        })
        .onStatus(statusCode -> statusCode.equals(HttpStatus.valueOf(403)), (req, response) -> {
            throw new RuntimeException("Not accepted email or you have not enough balance");
        })
        .onStatus(statusCode -> statusCode.equals(HttpStatus.valueOf(405)), (req, response) -> {
            throw new RuntimeException("Method not allowed, only POST method is allowed");
        })
        .onStatus(statusCode -> statusCode.equals(HttpStatus.valueOf(429)), (req, response) -> {
            throw new RuntimeException("Too many requests, please try again later");
        })
        .onStatus(statusCode -> statusCode.equals(HttpStatus.valueOf(500)), (req, response) -> {
            throw new RuntimeException("Internal server error");
        })
        .body(DadataResponse[].class);

        return Arrays.asList(responseAll);
    }
}