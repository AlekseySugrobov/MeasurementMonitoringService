package com.offsidegaming.measuremonitoringservice.mappers;

import com.offsidegaming.measuremonitoringservice.api.model.MeasurementDto;
import com.offsidegaming.measuremonitoringservice.api.model.MeasurementPageableResponseDto;
import com.offsidegaming.measuremonitoringservice.domain.entity.Measurement;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;

@Mapper(componentModel = "spring")
public interface MeasurementMapper {
    @Mapping(source = "userId", target = "userId")
    @Mapping(source = "type", target = "type")
    @Mapping(source = "value", target = "value")
    @Mapping(source = "date", target = "date")
    Measurement measurementInputDtoToEntity(MeasurementDto measurementInputDto);

    @Mapping(source = "totalElements", target = "total")
    @Mapping(source = "content", target = "content")
    MeasurementPageableResponseDto pageMeasurementsToMeasurementPageableResponseDto(Page<Measurement> measurementPage);
}
