package com.suraev.routeDestinationApp.entity;

import java.time.Instant;

import com.suraev.routeDestinationApp.dto.Coordinate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "coordinate_events")
public class CoordinateEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String sourceAdress;
    private Coordinate yandexCoordinate;
    private Coordinate dadataCoordinate;
    private Instant createdAt;
    
}

