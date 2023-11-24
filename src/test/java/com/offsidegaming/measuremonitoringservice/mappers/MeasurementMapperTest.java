package com.offsidegaming.measuremonitoringservice.mappers;

import com.offsidegaming.measuremonitoringservice.api.model.MeasurementDto;
import com.offsidegaming.measuremonitoringservice.api.model.MeasurementPageableResponseDto;
import com.offsidegaming.measuremonitoringservice.domain.entity.Measurement;
import com.offsidegaming.measuremonitoringservice.utils.PodamUtils;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;


@DisplayName("Тесты маппера Measurement")
class MeasurementMapperTest {

    private MeasurementMapper mapper = new MeasurementMapperImpl();

    @Test
    @DisplayName("Тест маппинга MeasurementInputDto в Measurement")
    void measurementInputDtoToEntity() {
        MeasurementDto measurementInputDto = PodamUtils.manufacturePojo(MeasurementDto.class);

        Measurement measurement = mapper.measurementInputDtoToEntity(measurementInputDto);

        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(measurement.getType()).isEqualTo(measurementInputDto.getType());
        softAssertions.assertThat(measurement.getDate()).isEqualTo(measurementInputDto.getDate());
        softAssertions.assertThat(measurement.getValue()).isEqualTo(measurementInputDto.getValue());
        softAssertions.assertThat(measurement.getUserId()).isEqualTo(measurementInputDto.getUserId());
        softAssertions.assertThat(measurement.getId()).isNull();
        softAssertions.assertAll();
    }

    @Test
    @DisplayName("Тест маппинга страницы Measurement в MeasurementPageableResponseDto")
    void pageMeasurementsToMeasurementPageableResponseDto() {
        List<Measurement> content = PodamUtils.manufacturePojo(List.class, Measurement.class);
        Page<Measurement> measurementPage = new PageImpl<>(content);

        MeasurementPageableResponseDto pageableResponse = mapper
                .pageMeasurementsToMeasurementPageableResponseDto(measurementPage);

        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(pageableResponse.getTotal()).isEqualTo(measurementPage.getTotalElements());
        softAssertions.assertThat(pageableResponse.getContent().size()).isEqualTo(content.size());
        softAssertions.assertAll();
    }
}