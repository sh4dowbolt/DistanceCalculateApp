package com.suraev.routeDestinationApp.dto.YandexDTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FeatureMember {
    @JsonProperty("GeoObject")
    private GeoObject geoObject;
}
