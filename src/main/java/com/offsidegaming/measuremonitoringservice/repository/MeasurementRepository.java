package com.offsidegaming.measuremonitoringservice.repository;

import com.offsidegaming.measuremonitoringservice.domain.entity.Measurement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

/**
 * Репозиторий для работы с {@link Measurement}.
 */
public interface MeasurementRepository extends JpaRepository<Measurement, Long> {
    /**
     * Проверяет наличие показаний с указанной датой.
     * @param date дата
     * @return true - если есть, false - если нет
     */
    boolean existsByUserIdAndMeasurementTypeAndDate(Long userId, String measurementType, LocalDate date);
}
