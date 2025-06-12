package com.suraev.routeDestinationApp.dto.YandexDTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GeoObject {
    @JsonProperty("Point")
    private Point point;
}
