package com.offsidegaming.measuremonitoringservice.in.controller;

import com.offsidegaming.measuremonitoringservice.api.MeasurementApi;
import com.offsidegaming.measuremonitoringservice.api.model.MeasurementInputDto;
import com.offsidegaming.measuremonitoringservice.api.model.MeasurementResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MeasurementController implements MeasurementApi {
    @Override
    public ResponseEntity<Void> measurementsPost(MeasurementInputDto measurementInput) {
        return MeasurementApi.super.measurementsPost(measurementInput);
    }

    @Override
    public ResponseEntity<List<MeasurementResponseDto>> measurementsUserIdGet(Integer userId) {
        return MeasurementApi.super.measurementsUserIdGet(userId);
    }
}
