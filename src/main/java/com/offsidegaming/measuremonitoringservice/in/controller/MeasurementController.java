package com.offsidegaming.measuremonitoringservice.in.controller;

import com.offsidegaming.measuremonitoringservice.api.MeasurementApi;
import com.offsidegaming.measuremonitoringservice.api.model.Measurement;
import com.offsidegaming.measuremonitoringservice.api.model.MeasurementInput;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MeasurementController implements MeasurementApi {
    @Override
    public ResponseEntity<Void> measurementsPost(MeasurementInput measurementInput) {
        return MeasurementApi.super.measurementsPost(measurementInput);
    }

    @Override
    public ResponseEntity<List<Measurement>> measurementsUserIdGet(Integer userId) {
        return MeasurementApi.super.measurementsUserIdGet(userId);
    }
}
