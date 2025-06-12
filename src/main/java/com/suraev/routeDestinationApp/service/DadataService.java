package com.suraev.routeDestinationApp.service;

import com.suraev.routeDestinationApp.dto.DadataRequest;
import com.suraev.routeDestinationApp.dto.DadataResponse;
import java.util.List;

public interface DadataService {
    List<DadataResponse> getAdress(DadataRequest request);
}
