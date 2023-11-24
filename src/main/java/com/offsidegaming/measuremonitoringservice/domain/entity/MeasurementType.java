package com.offsidegaming.measuremonitoringservice.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;

/**
 * Тип измерения.
 */
@Getter
@Entity
@Table(name = "measurement_types")
public class MeasurementType {
    /**
     * Код измерения.
     */
    @Id
    @Column(name = "code")
    private String code;
}
