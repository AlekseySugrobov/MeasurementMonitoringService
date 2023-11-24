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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Тесты MeasurementService")
class MeasurementServiceImplTest {

    private static final long USER_ID = 123;

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
        when(measurementTypeRepository.existsById(measurement.getType())).thenReturn(true);
        when(measurementRepository.existsByUserIdAndTypeAndDate(
                measurement.getUserId(), measurement.getType(), measurement.getDate())).thenReturn(false);
        when(this.measurementRepository.save(measurement)).thenReturn(measurement);

        Measurement savedMeasurement = measurementService.createMeasurement(measurement);

        verify(userRepository).existsById(measurement.getUserId());
        verify(measurementTypeRepository).existsById(measurement.getType());
        verify(measurementRepository).existsByUserIdAndTypeAndDate(
                measurement.getUserId(), measurement.getType(), measurement.getDate());
        verify(measurementRepository).save(measurement);

        assertThat(savedMeasurement).usingRecursiveComparison().isEqualTo(measurement);
    }

    @Test
    @DisplayName("Сохранение показания для неизвестного пользователя")
    void createMeasurementUserNotExist() {
        Measurement measurement = PodamUtils.manufacturePojo(Measurement.class);

        when(userRepository.existsById(measurement.getUserId())).thenReturn(false);
        when(measurementTypeRepository.existsById(measurement.getType())).thenReturn(true);
        when(measurementRepository.existsByUserIdAndTypeAndDate(
                measurement.getUserId(), measurement.getType(), measurement.getDate())).thenReturn(false);
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
        when(measurementTypeRepository.existsById(measurement.getType())).thenReturn(false);
        when(measurementRepository.existsByUserIdAndTypeAndDate(
                measurement.getUserId(), measurement.getType(), measurement.getDate())).thenReturn(false);
        when(this.measurementRepository.save(measurement)).thenReturn(measurement);

        assertThatThrownBy(() -> measurementService.createMeasurement(measurement))
                .isInstanceOf(UnknownMeasurementTypeException.class)
                .hasMessage("Unknown measurement type " + measurement.getType());
    }

    @Test
    @DisplayName("Сохранение показания. Конлфикт по дате и типу")
    void createMeasurementMeasurementConflict() {
        Measurement measurement = PodamUtils.manufacturePojo(Measurement.class);

        when(userRepository.existsById(measurement.getUserId())).thenReturn(true);
        when(measurementTypeRepository.existsById(measurement.getType())).thenReturn(true);
        when(measurementRepository.existsByUserIdAndTypeAndDate(
                measurement.getUserId(), measurement.getType(), measurement.getDate())).thenReturn(true);
        when(this.measurementRepository.save(measurement)).thenReturn(measurement);

        assertThatThrownBy(() -> measurementService.createMeasurement(measurement))
                .isInstanceOf(MeasurementConflictException.class)
                .hasMessage("Measurement with parameters " +
                        "[userId = " + measurement.getUserId() + ", type = " + measurement.getType() +
                        ", date = " + measurement.getDate() + "] already exists");
    }

    @Test
    @DisplayName("Тест постраничного получения измерений по идентификатору пользователя")
    void getMeasurementsPositiveCase() {
        Page<Measurement> measurements = new PageImpl<>(PodamUtils.manufacturePojo(List.class, Measurement.class));

        when(userRepository.existsById(USER_ID)).thenReturn(true);
        when(measurementRepository.findAllByUserId(eq(USER_ID), any(Pageable.class))).thenReturn(measurements);

        Page<Measurement> fromDatabase = measurementService.getMeasurements(USER_ID, 0, 10);

        verify(userRepository).existsById(USER_ID);
        verify(measurementRepository).findAllByUserId(eq(USER_ID), any(Pageable.class));
        assertThat(fromDatabase).usingRecursiveAssertion().isEqualTo(measurements);
    }

    @Test
    @DisplayName("Тест постраничного получения измерений по идентификатору пользователя. Пользователь не найден")
    void getMeasurementsUserNotFound() {

        when(userRepository.existsById(USER_ID)).thenReturn(false);

        assertThatThrownBy(() -> measurementService.getMeasurements(USER_ID, 0, 10))
                .isInstanceOf(UnknownUserIdException.class)
                .hasMessage("Unknown user id " + USER_ID);

    }

    @AfterEach
    public void resetStubs() {
        reset(userRepository);
        reset(measurementRepository);
        reset(measurementTypeRepository);
    }
}