package com.suraev.routeDestinationApp.service;

import com.suraev.routeDestinationApp.dto.YandexRequest;
import com.suraev.routeDestinationApp.dto.YandexResponse;

public interface YandeMapService {
    YandexResponse getCoordinates(YandexRequest request);

}
