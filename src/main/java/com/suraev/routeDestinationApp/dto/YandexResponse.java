package com.suraev.routeDestinationApp.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class YandexResponse {
    @JsonProperty(value = "metaDataProperty.GeocoderResponseMetaData.Point.pos")
    private String pos;    
}
