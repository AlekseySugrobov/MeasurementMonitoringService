package com.offsidegaming.measuremonitoringservice.mappers;

import com.offsidegaming.measuremonitoringservice.api.model.MeasurementInputDto;
import com.offsidegaming.measuremonitoringservice.domain.entity.Measurement;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MeasurementMapper {
    @Mapping(source = "userId", target = "userId")
    @Mapping(source = "type", target = "measurementType")
    @Mapping(source = "value", target = "value")
    @Mapping(source = "date", target = "date")
    Measurement measurementInputDtoToEntity(MeasurementInputDto measurementInputDto);
}
