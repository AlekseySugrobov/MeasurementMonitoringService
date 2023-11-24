package com.offsidegaming.measuremonitoringservice.service.impl;

import com.offsidegaming.measuremonitoringservice.domain.entity.Measurement;
import com.offsidegaming.measuremonitoringservice.exceptions.MeasurementConflictException;
import com.offsidegaming.measuremonitoringservice.exceptions.UnknownMeasurementTypeException;
import com.offsidegaming.measuremonitoringservice.exceptions.UnknownUserIdException;
import com.offsidegaming.measuremonitoringservice.repository.MeasurementRepository;
import com.offsidegaming.measuremonitoringservice.repository.MeasurementTypeRepository;
import com.offsidegaming.measuremonitoringservice.repository.UserRepository;
import com.offsidegaming.measuremonitoringservice.service.MeasurementService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * {@inheritDoc}
 */
@Service
@RequiredArgsConstructor
public class MeasurementServiceImpl implements MeasurementService {

    private final UserRepository userRepository;
    private final MeasurementRepository measurementRepository;
    private final MeasurementTypeRepository measurementTypeRepository;

    @Override
    public Measurement createMeasurement(Measurement measurement) {
        if (!this.measurementTypeRepository.existsById(measurement.getMeasurementType())) {
            throw new UnknownMeasurementTypeException("Unknown measurement type " + measurement.getMeasurementType());
        }
        if (!this.userRepository.existsById(measurement.getUserId())) {
            throw new UnknownUserIdException("Unknown user id " + measurement.getUserId());
        }
        if (this.measurementRepository.existsByUserIdAndMeasurementTypeAndDate(
                measurement.getUserId(), measurement.getMeasurementType(), measurement.getDate())) {
            throw new MeasurementConflictException("Measurement with parameters " +
                    "[userId = " + measurement.getUserId() + ", type = " + measurement.getMeasurementType() +
                    ", date = " + measurement.getDate() + "] already exists");
        }
        return this.measurementRepository.save(measurement);
    }
}
