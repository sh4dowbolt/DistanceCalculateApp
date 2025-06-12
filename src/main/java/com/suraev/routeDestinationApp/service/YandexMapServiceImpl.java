package com.suraev.routeDestinationApp.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import lombok.RequiredArgsConstructor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.suraev.routeDestinationApp.dto.YandexDTO.GeoObjectCollection;
import java.util.List;
import java.util.stream.Collectors;
import com.suraev.routeDestinationApp.dto.YandexDTO.FeatureMember;
import com.suraev.routeDestinationApp.dto.YandexDTO.GeoObject;
import com.suraev.routeDestinationApp.dto.YandexDTO.Point;
import com.suraev.routeDestinationApp.dto.CoordinateDTO;
import org.springframework.http.HttpStatus;
import com.suraev.routeDestinationApp.util.ExceptionResponseHandler;
import org.springframework.beans.factory.annotation.Value;

@Service
public class YandexMapServiceImpl implements YandexMapService {

    private final RestClient restClient;
    private final String yandexUrl;
    private final String apiKey;
    private final ObjectMapper objectMapper;


    public YandexMapServiceImpl(
        RestClient restClient,
        @Value("${yandex.url}") String yandexUrl,
        @Value("${yandex.apiKey}") String apiKey,
        ObjectMapper objectMapper
        ) {
        this.restClient = restClient;
        this.yandexUrl = yandexUrl;
        this.apiKey = apiKey;
        this.objectMapper = objectMapper;
    }


    @Override
    public CoordinateDTO getCoordinate(String [] adress) {


        //TODO: refactoring for multiple adresses
        if(adress.length>1) {
            throw new IllegalArgumentException("Adress must be one");
        }

        String adressString = adress[0];

        GeoObjectCollection geoObjectCollection = restClient.post()
        .uri(uriBuilder -> 
        uriBuilder.path(yandexUrl)
        .queryParam("apikey", apiKey)
        .queryParam("geocode", adressString)
        .queryParam("format", "json")
        .build())
        .retrieve()
        .onStatus(statusCode -> statusCode.equals(HttpStatus.valueOf(400)), (req, response) -> {
           ExceptionResponseHandler.handleStatus(response, objectMapper);
        })
        .onStatus(statusCode -> statusCode.equals(HttpStatus.valueOf(403)), (req, response) -> {
            ExceptionResponseHandler.handleStatus(response, objectMapper);
        })
        .onStatus(statusCode -> statusCode.equals(HttpStatus.INTERNAL_SERVER_ERROR), (req, response) -> {
            ExceptionResponseHandler.handleStatus(response, objectMapper);
        })
        .body(GeoObjectCollection.class);

        if(geoObjectCollection == null) {
            throw new IllegalArgumentException("No point found");
        }

        String pos= geoObjectCollection.getFeatureMember().stream()
        .map(FeatureMember::getGeoObject)
        .map(GeoObject::getPoint)
        .map(Point::getPos)
        .findFirst()
        .orElseThrow(() -> new IllegalArgumentException("No point found"));

        return convertToCoordinateDTO(pos);
    }

    private CoordinateDTO convertToCoordinateDTO(String pos) {
        String[] coordinates = pos.split(" ");
        CoordinateDTO coordinateDTO = new CoordinateDTO();
        coordinateDTO.setLatitude(coordinates[0]);
        coordinateDTO.setLongitude(coordinates[1]);
        return coordinateDTO;
    }
}