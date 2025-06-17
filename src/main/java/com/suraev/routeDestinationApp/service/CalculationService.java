package com.suraev.routeDestinationApp.service;

import com.suraev.routeDestinationApp.dto.CoordinatePosResponse;
import com.suraev.routeDestinationApp.dto.MeasureType;
import com.suraev.routeDestinationApp.exception.BadRequestException;

public interface CalculationService {
    CoordinatePosResponse calculateDistance(String [] adress, MeasureType measureType) throws BadRequestException;
}
