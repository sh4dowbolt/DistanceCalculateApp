package com.suraev.routeDestinationApp.service;

import com.suraev.routeDestinationApp.dto.BadRequestException;
import com.suraev.routeDestinationApp.dto.CoordinatePosResponse;
import com.suraev.routeDestinationApp.dto.MeasureType;

public interface CalculationService {

    CoordinatePosResponse calculateDistance(String [] adress, MeasureType measureType) throws BadRequestException;

}
