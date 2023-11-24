package com.offsidegaming.measuremonitoringservice.service.impl;

import com.offsidegaming.measuremonitoringservice.domain.entity.Measurement;
import com.offsidegaming.measuremonitoringservice.exceptions.MeasurementConflictException;
import com.offsidegaming.measuremonitoringservice.exceptions.UnknownMeasurementTypeException;
import com.offsidegaming.measuremonitoringservice.exceptions.UnknownUserIdException;
import com.offsidegaming.measuremonitoringservice.repository.MeasurementRepository;
import com.offsidegaming.measuremonitoringservice.repository.MeasurementTypeRepository;
import com.offsidegaming.measuremonitoringservice.repository.UserRepository;
import com.offsidegaming.measuremonitoringservice.service.MeasurementService;
import com.offsidegaming.measuremonitoringservice.utils.PodamUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.Extensions;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Тесты MeasurementService")
class MeasurementServiceImplTest {
    @Mock
    private MeasurementRepository measurementRepository;
    @Mock
    private MeasurementTypeRepository measurementTypeRepository;
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private MeasurementServiceImpl measurementService;


    @Test
    @DisplayName("Сохранение показаний. Позитивный кейс")
    void createMeasurementPositiveCase() {
        Measurement measurement = PodamUtils.manufacturePojo(Measurement.class);

        when(userRepository.existsById(measurement.getUserId())).thenReturn(true);
        when(measurementTypeRepository.existsById(measurement.getMeasurementType())).thenReturn(true);
        when(measurementRepository.existsByUserIdAndMeasurementTypeAndDate(
                measurement.getUserId(), measurement.getMeasurementType(), measurement.getDate())).thenReturn(false);
        when(this.measurementRepository.save(measurement)).thenReturn(measurement);

        Measurement savedMeasurement = measurementService.createMeasurement(measurement);

        verify(userRepository).existsById(measurement.getUserId());
        verify(measurementTypeRepository).existsById(measurement.getMeasurementType());
        verify(measurementRepository).existsByUserIdAndMeasurementTypeAndDate(
                measurement.getUserId(), measurement.getMeasurementType(), measurement.getDate());
        verify(measurementRepository).save(measurement);

        assertThat(savedMeasurement).usingRecursiveComparison().isEqualTo(measurement);
    }

    @Test
    @DisplayName("Сохранение показания для неизвестного пользователя")
    void createMeasurementUserNotExist() {
        Measurement measurement = PodamUtils.manufacturePojo(Measurement.class);

        when(userRepository.existsById(measurement.getUserId())).thenReturn(false);
        when(measurementTypeRepository.existsById(measurement.getMeasurementType())).thenReturn(true);
        when(measurementRepository.existsByUserIdAndMeasurementTypeAndDate(
                measurement.getUserId(), measurement.getMeasurementType(), measurement.getDate())).thenReturn(false);
        when(this.measurementRepository.save(measurement)).thenReturn(measurement);

        assertThatThrownBy(() -> measurementService.createMeasurement(measurement))
                .isInstanceOf(UnknownUserIdException.class)
                .hasMessage("Unknown user id " + measurement.getUserId());
    }

    @Test
    @DisplayName("Сохранение показания с неизвестным типом")
    void createMeasurementMeasurementTypeNotExist() {
        Measurement measurement = PodamUtils.manufacturePojo(Measurement.class);

        when(userRepository.existsById(measurement.getUserId())).thenReturn(true);
        when(measurementTypeRepository.existsById(measurement.getMeasurementType())).thenReturn(false);
        when(measurementRepository.existsByUserIdAndMeasurementTypeAndDate(
                measurement.getUserId(), measurement.getMeasurementType(), measurement.getDate())).thenReturn(false);
        when(this.measurementRepository.save(measurement)).thenReturn(measurement);

        assertThatThrownBy(() -> measurementService.createMeasurement(measurement))
                .isInstanceOf(UnknownMeasurementTypeException.class)
                .hasMessage("Unknown measurement type " + measurement.getMeasurementType());
    }

    @Test
    @DisplayName("Сохранение показания. Конлфикт по дате и типу")
    void createMeasurementMeasurementConflict() {
        Measurement measurement = PodamUtils.manufacturePojo(Measurement.class);

        when(userRepository.existsById(measurement.getUserId())).thenReturn(true);
        when(measurementTypeRepository.existsById(measurement.getMeasurementType())).thenReturn(true);
        when(measurementRepository.existsByUserIdAndMeasurementTypeAndDate(
                measurement.getUserId(), measurement.getMeasurementType(), measurement.getDate())).thenReturn(true);
        when(this.measurementRepository.save(measurement)).thenReturn(measurement);

        assertThatThrownBy(() -> measurementService.createMeasurement(measurement))
                .isInstanceOf(MeasurementConflictException.class)
                .hasMessage("Measurement with parameters " +
                        "[userId = " + measurement.getUserId() + ", type = " + measurement.getMeasurementType() +
                        ", date = " + measurement.getDate() + "] already exists");
    }

    @AfterEach
    public void resetStubs() {
        reset(userRepository);
        reset(measurementRepository);
        reset(measurementTypeRepository);
    }
}