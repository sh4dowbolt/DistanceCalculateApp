package com.suraev.routeDestinationApp.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import lombok.RequiredArgsConstructor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.suraev.routeDestinationApp.dto.YandexDTO.GeoObjectCollection;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import com.suraev.routeDestinationApp.dto.YandexDTO.FeatureMember;
import com.suraev.routeDestinationApp.dto.YandexDTO.GeoObject;
import com.suraev.routeDestinationApp.dto.YandexDTO.Point;
import com.suraev.routeDestinationApp.dto.BadRequestException;
import com.suraev.routeDestinationApp.dto.CoordinateDTO;
import org.springframework.http.HttpStatus;
import com.suraev.routeDestinationApp.util.ExceptionResponseHandler;
import org.springframework.beans.factory.annotation.Value;
import java.net.URI;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import com.suraev.routeDestinationApp.dto.YandexDTO.YandexResponse;




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
    public CoordinateDTO getCoordinate(String [] adress) throws BadRequestException {
        String adressString = adress[0];
        URI uri = URI.create(yandexUrl + "?apikey=" + apiKey + "&geocode=" + 
            URLEncoder.encode(adressString, StandardCharsets.UTF_8) + "&format=json");

        System.out.println(uri.toString());

        YandexResponse yandexResponse = restClient.get()
            .uri(uri)
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
            .body(YandexResponse.class);

        if (yandexResponse == null || yandexResponse.getResponse() == null || 
            yandexResponse.getResponse().getGeoObjectCollection() == null || 
            yandexResponse.getResponse().getGeoObjectCollection().getFeatureMember() == null || 
            yandexResponse.getResponse().getGeoObjectCollection().getFeatureMember().isEmpty()) {
            throw new BadRequestException("Адрес не найден: " + adressString, "/getDistance");
        }

        String pos = yandexResponse.getResponse().getGeoObjectCollection().getFeatureMember().stream()
            .map(FeatureMember::getGeoObject)
            .map(GeoObject::getPoint)
            .map(Point::getPos)
            .findFirst()
            .orElseThrow(() -> new BadRequestException("Координаты не найдены для адреса: " + adressString, "/getDistance"));

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