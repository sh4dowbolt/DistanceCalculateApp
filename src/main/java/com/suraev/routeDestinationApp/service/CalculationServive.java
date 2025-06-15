package com.suraev.routeDestinationApp.service;

import com.suraev.routeDestinationApp.dto.BadRequestException;
import com.suraev.routeDestinationApp.dto.CoordinatePosResponse;

public interface CalculationServive {

    CoordinatePosResponse calculateDistance(String [] adress) throws BadRequestException;

}
