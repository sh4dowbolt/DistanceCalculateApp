package com.suraev.routeDestinationApp.service;

import com.suraev.routeDestinationApp.dto.DadataResponse;
import com.suraev.routeDestinationApp.dto.ExceptionResponse;
import com.suraev.routeDestinationApp.dto.BadRequestException;
import com.suraev.routeDestinationApp.dto.CoordinateDTO;

public interface DadataService {

    CoordinateDTO getCoordinate(String [] adress) throws ExceptionResponse, BadRequestException;
}
