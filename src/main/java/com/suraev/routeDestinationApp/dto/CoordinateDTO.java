package com.suraev.routeDestinationApp.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;   

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class CoordinateDTO {
    @JsonProperty("geo_lat")
    private String latitude;
    @JsonProperty("geo_lon")
    private String longitude;
}
