package com.suraev.routeDestinationApp.entity;

import java.time.Instant;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Column;

@Entity
@Table(name = "calculate_events")
public class DifferenceCoordinateRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "source_adress")
    private String sourceAdress;
    @Column(name = "create_date")
    private Instant createdAt;
    @Column(name = "distance")
    private Double distance;
    @OneToOne
    private Coordinate yandexCoordinate;
    @OneToOne
    private Coordinate dadataCoordinate;
}


