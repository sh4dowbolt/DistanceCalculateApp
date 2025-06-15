package com.suraev.routeDestinationApp.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CoordinatePosResponse {
    private double difference;
    private MeasureType measureType;
}
