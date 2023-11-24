package com.offsidegaming.measuremonitoringservice.repository;

import com.offsidegaming.measuremonitoringservice.domain.entity.Measurement;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    boolean existsByUserIdAndTypeAndDate(Long userId, String measurementType, LocalDate date);

    /**
     * Постраничный поиск показаний по идентификатору пользователя.
     * @param userId идентификатор пользователя
     * @param pageable {@link Pageable}
     * @return страница {@link Measurement}
     */
    Page<Measurement> findAllByUserId(long userId, Pageable pageable);
}
