package com.suraev.routeDestinationApp.service;

import com.suraev.routeDestinationApp.dto.CoordinateDTO;
import com.suraev.routeDestinationApp.exception.BadRequestException;
import com.suraev.routeDestinationApp.exception.ExceptionResponse;

public interface YandexMapService {
    CoordinateDTO getCoordinate(String [] adress) throws BadRequestException, ExceptionResponse;
}
