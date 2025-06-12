package com.suraev.routeDestinationApp.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import lombok.RequiredArgsConstructor;
import com.suraev.routeDestinationApp.dto.YandexRequest;
import com.suraev.routeDestinationApp.dto.YandexResponse;

@Service
@RequiredArgsConstructor
public class YandexMapServiceImpl implements YandexMapService {

    private final RestClient restClient;

    @Override
    public YandexResponse getCoordinates(YandexRequest request) {
        return null;
    }

  
}