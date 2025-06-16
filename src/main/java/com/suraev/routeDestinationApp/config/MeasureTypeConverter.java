package com.suraev.routeDestinationApp.config;

import com.suraev.routeDestinationApp.dto.MeasureType;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class MeasureTypeConverter implements Converter<String, MeasureType> {
    
    @Override
    public MeasureType convert(String source) {
        try {
            return MeasureType.valueOf(source.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid measure type. Allowed values are: KM, M, CENTIMETER");
        }
    }
} 