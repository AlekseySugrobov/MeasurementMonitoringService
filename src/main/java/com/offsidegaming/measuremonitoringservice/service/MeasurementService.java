package com.offsidegaming.measuremonitoringservice.service;

import com.offsidegaming.measuremonitoringservice.domain.entity.Measurement;

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
}
