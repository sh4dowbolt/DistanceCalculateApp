package com.suraev.routeDestinationApp.service;

import com.suraev.routeDestinationApp.dto.DadataResponse;
import java.util.List;

public interface DadataService {
    List<DadataResponse> getCoordinate(String[] adresses);
}
