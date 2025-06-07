package com.suraev.routeDestinationApp.service;

import org.springframework.stereotype.Service;

import com.suraev.routeDestinationApp.dto.DadataRequest;
import com.suraev.routeDestinationApp.dto.DadataResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.client.RestClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.HttpStatus;



@Service
@RequiredArgsConstructor
public class DadataServiceImpl implements DadataService {

    private final RestClient restClient;
    @Value("${dadata.url}")
    private final String ddataUrl;
    @Value("${dadata.apiKey}")
    private final String apiKey;
    @Value("${dadata.secretKey}")
    private final String secretKey;

    @Override
    public DadataResponse getAdress(DadataRequest request) {
        
         restClient.post()
        .uri(ddataUrl)
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON)
        .body(request)
        .header("Authorization", "Token " + apiKey)
        .header("X-Secret", secretKey)
        .retrieve()
        .onStatus(statusCode -> statusCode.equals(HttpStatus.BAD_REQUEST), (req, response) -> {
            throw new RuntimeException("Bad Request");
        })
        .body(DadataResponse.class);

        return null;
    }
}