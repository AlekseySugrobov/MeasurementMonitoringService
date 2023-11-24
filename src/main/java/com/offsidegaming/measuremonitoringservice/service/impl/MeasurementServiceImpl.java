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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

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
        if (!this.measurementTypeRepository.existsById(measurement.getType())) {
            throw new UnknownMeasurementTypeException("Unknown measurement type " + measurement.getType());
        }
        if (!this.userRepository.existsById(measurement.getUserId())) {
            throw new UnknownUserIdException("Unknown user id " + measurement.getUserId());
        }
        if (this.measurementRepository.existsByUserIdAndTypeAndDate(
                measurement.getUserId(), measurement.getType(), measurement.getDate())) {
            throw new MeasurementConflictException("Measurement with parameters " +
                    "[userId = " + measurement.getUserId() + ", type = " + measurement.getType() +
                    ", date = " + measurement.getDate() + "] already exists");
        }
        return this.measurementRepository.save(measurement);
    }

    @Override
    public Page<Measurement> getMeasurements(long userId, int page, int size) {
        if (!this.userRepository.existsById(userId)) {
            throw new UnknownUserIdException("Unknown user id " + userId);
        }
        return this.measurementRepository.findAllByUserId(userId, PageRequest.of(page, size, Sort.by("date").ascending()));
    }
}
