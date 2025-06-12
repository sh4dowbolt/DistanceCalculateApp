package com.suraev.routeDestinationApp.service;

import com.suraev.routeDestinationApp.dto.DadataResponse;
import com.suraev.routeDestinationApp.dto.ExceptionResponse;

public interface DadataService {

    DadataResponse getCoordinate(String []  adress) throws ExceptionResponse;
}
