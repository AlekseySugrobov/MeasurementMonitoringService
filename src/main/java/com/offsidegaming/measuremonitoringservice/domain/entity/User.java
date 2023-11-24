package com.offsidegaming.measuremonitoringservice.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;

/**
 * Пользователь.
 */
@Getter
@Entity
@Table(name = "users")
public class User {
    /**
     * Идентификатор.
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Имя пользователя.
     */
    @Column(name = "username")
    private String userName;
}
