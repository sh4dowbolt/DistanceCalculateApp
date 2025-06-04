package com.suraev.routeDestinationApp.service;

import com.suraev.routeDestinationApp.dto.DadataRequest;
import com.suraev.routeDestinationApp.dto.DadataResponse;

public interface DadataService {
    DadataResponse getAdress(DadataRequest request);
}
