package com.offsidegaming.measuremonitoringservice.service;

import com.offsidegaming.measuremonitoringservice.domain.entity.Measurement;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * Сервис, реализующий логику работы с Measurement.
 */
public interface MeasurementService {
    /**
     * Сохранения показаний.
     * @param measurement показания определенного типа {@link  Measurement}
     * @return сохраненные показания {@link  Measurement}
     */
    Measurement createMeasurement(Measurement measurement);

    /**
     * Постраничное получение показаний по идентификатору пользователя.
     * @param userId идентификатор пользователя
     * @param page номер страницы
     * @param size количество элементов на странице
     * @return
     */
    Page<Measurement> getMeasurements(long userId, int page, int size);
}
