package com.offsidegaming.measuremonitoringservice.repository;

import com.offsidegaming.measuremonitoringservice.domain.entity.Measurement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MeasurementRepository extends JpaRepository<Measurement, Long> {
}
