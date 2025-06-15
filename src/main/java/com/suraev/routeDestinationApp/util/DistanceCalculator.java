package com.suraev.routeDestinationApp.util;

import com.suraev.routeDestinationApp.dto.CoordinateDTO;

public class DistanceCalculator {

    private static final double EARTH_RADIUS_KM = 6371.0;

    public static double calculateDistance(double lat1, double lon1, double lat2, double lon2) {

        double lat1Rad = Math.toRadians(lat1);
        double lon1Rad = Math.toRadians(lon1);
        double lat2Rad = Math.toRadians(lat2);
        double lon2Rad = Math.toRadians(lon2);

        double dLat = lat2Rad - lat1Rad;
        double dLon = lon2Rad - lon1Rad;

        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(lat1Rad) * Math.cos(lat2Rad) *
                Math.sin(dLon / 2) * Math.sin(dLon / 2);
        
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        
        return EARTH_RADIUS_KM * c;
    }

    public static double calculateDistanceFromStrings(CoordinateDTO coordinate1, CoordinateDTO coordinate2) {
        
        String cord1Lat = coordinate1.getLatitude();
        String cord1Lon = coordinate1.getLongitude();
        String cord2Lat = coordinate2.getLatitude();
        String cord2Lon = coordinate2.getLongitude();
    
        return calculateDistance(
            Double.parseDouble(cord1Lat),
            Double.parseDouble(cord1Lon),
            Double.parseDouble(cord2Lat),       
            Double.parseDouble(cord2Lon)
        );
    }
} 