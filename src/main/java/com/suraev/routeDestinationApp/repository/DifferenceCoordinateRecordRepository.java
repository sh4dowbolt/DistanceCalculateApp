package com.suraev.routeDestinationApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.suraev.routeDestinationApp.entity.DifferenceCoordinateRecord;

public interface DifferenceCoordinateRecordRepository extends JpaRepository<DifferenceCoordinateRecord, Long> {
    
}
