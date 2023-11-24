package com.offsidegaming.measuremonitoringservice.in.controller;

import com.offsidegaming.measuremonitoringservice.api.MeasurementApi;
import com.offsidegaming.measuremonitoringservice.api.model.MeasurementInputDto;
import com.offsidegaming.measuremonitoringservice.api.model.MeasurementResponseDto;
import com.offsidegaming.measuremonitoringservice.domain.entity.Measurement;
import com.offsidegaming.measuremonitoringservice.mappers.MeasurementMapper;
import com.offsidegaming.measuremonitoringservice.service.MeasurementService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MeasurementController implements MeasurementApi {

    private final MeasurementMapper measurementMapper;
    private final MeasurementService measurementService;

    @Override
    public ResponseEntity<Void> measurementsPost(MeasurementInputDto measurementInput) {
        Measurement measurement = measurementMapper.measurementInputDtoToEntity(measurementInput);
        this.measurementService.createMeasurement(measurement);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Override
    public ResponseEntity<List<MeasurementResponseDto>> measurementsUserIdGet(Integer userId) {
        return MeasurementApi.super.measurementsUserIdGet(userId);
    }
}
