package com.suraev.routeDestinationApp.util;

import com.suraev.routeDestinationApp.dto.CoordinateDTO;


public class ConvertCoordinate {
    public static CoordinateDTO convertToCoordinateDTO(String pos) {
        String[] coordinates = pos.split(" ");
        return new CoordinateDTO(coordinates[0], coordinates[1]);
    }
}
