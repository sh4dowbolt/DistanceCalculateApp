package com.suraev.routeDestinationApp.util;

import com.suraev.routeDestinationApp.dto.CoordinateDTO;


public class ConvertCoordinate {
    public static CoordinateDTO convertToCoordinateDTO(String pos) {
        String[] coordinates = pos.split(" ");
        CoordinateDTO coordinateDTO = new CoordinateDTO();
        String longitude = coordinates[0];
        String latitude = coordinates[1];

        coordinateDTO.setLongitude(longitude);
        coordinateDTO.setLatitude(latitude);
        return coordinateDTO;
    }
}
