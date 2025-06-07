package com.suraev.routeDestinationApp.service;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CalculationServiceImpl implements CalculationServive {

    private final YandexMapService yandexMapService;
    private final DadataService dadataService;

    @Override
    public BigDecimal calculateDistance(String adress) {
        return null;
    }

}

   