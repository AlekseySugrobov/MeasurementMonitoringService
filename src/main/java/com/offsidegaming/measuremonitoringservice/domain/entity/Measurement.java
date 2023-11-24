package com.offsidegaming.measuremonitoringservice.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Показания.
 */
@Getter
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
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    /**
     * Тип измерения.
     */
    @Column(name = "measurement_type_name", nullable = false)
    private String measurementType;

    /**
     * Значение.
     */
    @Column(name = "value", nullable = false)
    private BigDecimal value;

    /**
     * Дата подачи.
     */
    @Column(name = "timestamp", nullable = false)
    private LocalDateTime date;
}
