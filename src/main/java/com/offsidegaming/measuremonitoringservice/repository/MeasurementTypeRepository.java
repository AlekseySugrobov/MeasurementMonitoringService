package com.offsidegaming.measuremonitoringservice.repository;

import com.offsidegaming.measuremonitoringservice.domain.entity.MeasurementType;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Репозиторий для работы с {@link  MeasurementType}.
 */
public interface MeasurementTypeRepository extends JpaRepository<MeasurementType, String> {

}
