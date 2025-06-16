package com.suraev.routeDestinationApp.service;

import org.springframework.stereotype.Service;
import com.suraev.routeDestinationApp.dto.CoordinateDTO;
import com.suraev.routeDestinationApp.repository.DifferenceCoordinateRecordRepository;
import com.suraev.routeDestinationApp.util.DistanceCalculator;
import com.suraev.routeDestinationApp.util.StringValidator;
import com.suraev.routeDestinationApp.service.CalculationService;
import com.suraev.routeDestinationApp.service.YandexMapService;
import com.suraev.routeDestinationApp.service.DadataService;
import com.suraev.routeDestinationApp.dto.BadRequestException;
import com.suraev.routeDestinationApp.entity.DifferenceCoordinateRecord;
import com.suraev.routeDestinationApp.entity.Coordinate;
import java.time.Instant;
import com.suraev.routeDestinationApp.dto.CoordinatePosResponse;
import com.suraev.routeDestinationApp.dto.MeasureType;
import com.suraev.routeDestinationApp.dto.CoordinatesPair;
import lombok.RequiredArgsConstructor;  

@Service
@RequiredArgsConstructor
public class CalculationServiceImpl implements CalculationService {

    private final YandexMapService yandexMapService;
    private final DadataService dadataService;
    private final DifferenceCoordinateRecordRepository differenceCoordinateRepository;

    @Override
    public CoordinatePosResponse calculateDistance(String[] adress, MeasureType measureType) throws BadRequestException {
       
        String adressFromRequest = adress[0];
        if (!StringValidator.isValidUTF8(adressFromRequest)) {
            throw new BadRequestException("Incorrect data format, must use UTF-8 encoding", adressFromRequest)
        }

        CoordinatesPair cordsFromServices = getCoordinatesFromServices(adress);
        double distanceKm = DistanceCalculator.calculateDistance(cordsFromServices);

        if(measureType != MeasureType.M) {
            double convertedDistance = DistanceCalculator.convertDistance(distanceKm, measureType);
        }
        
        saveCalculationResult(adress, distanceKm, cordsFromServices);

        return createResponse(distanceKm, measureType);
    }

    private void saveCalculationResult(String [] adress, double distance,
                                       CoordinatesPair coordinatesPair) {
        DifferenceCoordinateRecord differenceCoordinateRecord = new DifferenceCoordinateRecord();
        differenceCoordinateRecord.setSourceAdress(adress[0]);
        differenceCoordinateRecord.setCreatedAt(Instant.now());
        differenceCoordinateRecord.setDistance(distance);
        differenceCoordinateRecord.setDadataCoordinate(coordinatesPair.dadataEntity());
        differenceCoordinateRecord.setYandexCoordinate(coordinatesPair.yandexEntity());

        differenceCoordinateRepository.save(differenceCoordinateRecord);
    }

    private CoordinatesPair getCoordinatesFromServices(String [] adress) throws BadRequestException {
        CoordinateDTO dadataCord = dadataService.getCoordinate(adress);
        CoordinateDTO yandexCord = yandexMapService.getCoordinate(adress);

        return new CoordinatesPair(
            toCoordinate(dadataCord),
            toCoordinate(yandexCord),
            dadataCord,
            yandexCord);
    }

    private CoordinatePosResponse createResponse(double distance,  MeasureType measureType) {
        CoordinatePosResponse response = new CoordinatePosResponse();
        response.setDifference(distance);
        response.setMeasureType(measureType);
        return response;
    }

    private Coordinate toCoordinate(CoordinateDTO coordinateDTO) {
        Coordinate coordinate = new Coordinate();
        coordinate.setLatitude(coordinateDTO.getLatitude());
        coordinate.setLongitude(coordinateDTO.getLongitude());
        return coordinate;
    }
}

   