package com.suraev.routeDestinationApp.service;

import org.springframework.stereotype.Service;
import com.suraev.routeDestinationApp.dto.CoordinateDTO;
import com.suraev.routeDestinationApp.repository.DifferenceCoordinateRecordRepository;
import com.suraev.routeDestinationApp.util.DistanceCalculator;
import com.suraev.routeDestinationApp.service.CalculationServive;
import com.suraev.routeDestinationApp.service.YandexMapService;
import com.suraev.routeDestinationApp.service.DadataService;
import com.suraev.routeDestinationApp.dto.BadRequestException;
import com.suraev.routeDestinationApp.entity.DifferenceCoordinateRecord;
import com.suraev.routeDestinationApp.entity.Coordinate;
import java.time.Instant;
import com.suraev.routeDestinationApp.dto.CoordinatePosResponse;
import com.suraev.routeDestinationApp.dto.MeasureType;

import lombok.RequiredArgsConstructor;  

@Service
@RequiredArgsConstructor
public class CalculationServiceImpl implements CalculationServive {

    private final YandexMapService yandexMapService;
    private final DadataService dadataService;
    private final DifferenceCoordinateRecordRepository differenceCoordinateRecordRepository;

    @Override
    public CoordinatePosResponse calculateDistance(String [] adress) throws BadRequestException {
        CoordinateDTO dadataCord = dadataService.getCoordinate(adress);
        CoordinateDTO yandexCord = yandexMapService.getCoordinate(adress);

        Coordinate dadataCoordinate = mapToCoordinate(dadataCord);
        Coordinate yandexCoordinate = mapToCoordinate(yandexCord);

        double distance = DistanceCalculator.calculateDistanceFromStrings(dadataCord, yandexCord);

        DifferenceCoordinateRecord differenceCoordinateRecord = saveDifferenceCoordinateRecord(adress, distance, dadataCoordinate, yandexCoordinate);
  
        CoordinatePosResponse coordinatePosResponse = new CoordinatePosResponse();
        coordinatePosResponse.setDifference(distance);
        coordinatePosResponse.setMeasureType(MeasureType.M);
        return coordinatePosResponse;
    }

    private DifferenceCoordinateRecord saveDifferenceCoordinateRecord(String [] adress, 
    double distance, Coordinate dadataCoordinate, Coordinate yandexCoordinate) {
        DifferenceCoordinateRecord differenceCoordinateRecord = new DifferenceCoordinateRecord();
        differenceCoordinateRecord.setSourceAdress(adress[0]);
        differenceCoordinateRecord.setCreatedAt(Instant.now());
        differenceCoordinateRecord.setDistance(distance);
        differenceCoordinateRecord.setDadataCoordinate(dadataCoordinate);
        differenceCoordinateRecord.setYandexCoordinate(yandexCoordinate);
        return differenceCoordinateRecord;
    }

    private Coordinate mapToCoordinate(CoordinateDTO coordinateDTO) {
        Coordinate coordinate = new Coordinate();
        coordinate.setLatitude(coordinateDTO.getLatitude());
        coordinate.setLongitude(coordinateDTO.getLongitude());
        return coordinate;
    }
}

   