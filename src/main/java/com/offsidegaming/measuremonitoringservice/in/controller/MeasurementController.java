package com.offsidegaming.measuremonitoringservice.in.controller;

import com.offsidegaming.measuremonitoringservice.api.MeasurementApi;
import com.offsidegaming.measuremonitoringservice.api.model.MeasurementDto;
import com.offsidegaming.measuremonitoringservice.api.model.MeasurementPageableResponseDto;
import com.offsidegaming.measuremonitoringservice.domain.entity.Measurement;
import com.offsidegaming.measuremonitoringservice.mappers.MeasurementMapper;
import com.offsidegaming.measuremonitoringservice.service.MeasurementService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
public class MeasurementController implements MeasurementApi {

    private final MeasurementMapper measurementMapper;
    private final MeasurementService measurementService;

    @Override
    public ResponseEntity<Void> measurementsPost(MeasurementDto measurementInput) {
        Measurement measurement = measurementMapper.measurementInputDtoToEntity(measurementInput);
        this.measurementService.createMeasurement(measurement);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Override
    public ResponseEntity<MeasurementPageableResponseDto> measurementsUserIdGet(Long userId, Integer page, Integer size) {
        Page<Measurement> measurementPage = measurementService.getMeasurements(userId, page, size);
        return ResponseEntity.ok(measurementMapper.pageMeasurementsToMeasurementPageableResponseDto(measurementPage));
    }
}
