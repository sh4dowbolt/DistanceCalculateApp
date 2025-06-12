package com.suraev.routeDestinationApp.service;

import com.suraev.routeDestinationApp.dto.CoordinateDTO;

public interface YandexMapService {
    CoordinateDTO getCoordinate(String [] adress);

    
}
