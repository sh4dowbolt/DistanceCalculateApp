package com.suraev.routeDestinationApp.service;

import com.suraev.routeDestinationApp.exception.ExceptionResponse;
import com.suraev.routeDestinationApp.exception.BadRequestException;
import com.suraev.routeDestinationApp.dto.CoordinateDTO;

public interface DadataService {

    CoordinateDTO getCoordinate(String [] adress) throws ExceptionResponse, BadRequestException;
}
