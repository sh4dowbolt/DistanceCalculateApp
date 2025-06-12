package com.suraev.routeDestinationApp.dto.YandexDTO;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GeoObjectCollection {
    @JsonProperty("featureMember")
    private List<FeatureMember> featureMember;
}

