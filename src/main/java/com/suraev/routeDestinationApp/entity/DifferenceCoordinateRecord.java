package com.suraev.routeDestinationApp.entity;

import java.time.Instant;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.Column;

@Entity
@Table(name = "calculate_events")
@Getter
@Setter
public class DifferenceCoordinateRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "source_address")
    private String sourceAddress;
    @Column(name = "create_date")
    private Instant createdAt;
    @Column(name = "distance")
    private Double distance;
    @OneToOne(cascade = CascadeType.ALL)
    private Coordinate yandexCoordinate;
    @OneToOne(cascade = CascadeType.ALL)
    private Coordinate dadataCoordinate;
}


