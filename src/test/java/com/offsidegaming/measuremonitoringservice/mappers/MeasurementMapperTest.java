package com.offsidegaming.measuremonitoringservice.mappers;

import com.offsidegaming.measuremonitoringservice.api.model.MeasurementInputDto;
import com.offsidegaming.measuremonitoringservice.domain.entity.Measurement;
import com.offsidegaming.measuremonitoringservice.utils.PodamUtils;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


@DisplayName("Тесты маппера Measurement")
class MeasurementMapperTest {

    private MeasurementMapper mapper = new MeasurementMapperImpl();

    @Test
    @DisplayName("Тест маппинга MeasurementInputDto в Measurement")
    void measurementInputDtoToEntity() {
        MeasurementInputDto measurementInputDto = PodamUtils.manufacturePojo(MeasurementInputDto.class);

        Measurement measurement = mapper.measurementInputDtoToEntity(measurementInputDto);

        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(measurement.getMeasurementType()).isEqualTo(measurementInputDto.getType());
        softAssertions.assertThat(measurement.getDate()).isEqualTo(measurementInputDto.getDate());
        softAssertions.assertThat(measurement.getValue()).isEqualTo(measurementInputDto.getValue());
        softAssertions.assertThat(measurement.getUserId()).isEqualTo(measurementInputDto.getUserId());
        softAssertions.assertThat(measurement.getId()).isNull();
        softAssertions.assertAll();
    }
}