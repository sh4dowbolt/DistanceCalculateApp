package com.suraev.routeDestinationApp.dto;

import com.suraev.routeDestinationApp.entity.Coordinate;

public record CoordinatesPair(
    Coordinate dadataEntity,
    Coordinate yandexEntity,
    CoordinateDTO dadataDTO,
    CoordinateDTO yandexDTO
) {
    
}
