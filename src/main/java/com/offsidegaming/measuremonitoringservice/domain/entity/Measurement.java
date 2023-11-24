package com.offsidegaming.measuremonitoringservice.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Показания.
 */
@Getter
@Setter
@Entity
@Table(name = "measurements")
public class Measurement {
    /**
     * Идентификатор.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Пользователь.
     */
    @NotNull
    @Column(name = "user_id")
    private Long userId;

    /**
     * Тип измерения.
     */
    @NotEmpty
    @Column(name = "measurement_type_name", nullable = false)
    private String type;

    /**
     * Значение.
     */
    @Positive
    @Column(name = "value", nullable = false)
    private double value;

    /**
     * Дата подачи.
     */
    @Column(name = "date", nullable = false)
    private LocalDate date;
}
