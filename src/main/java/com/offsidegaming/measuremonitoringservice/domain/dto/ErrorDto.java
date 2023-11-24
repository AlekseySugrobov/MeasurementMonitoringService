package com.offsidegaming.measuremonitoringservice.domain.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Ошибочный ответ.
 */
@Getter
@RequiredArgsConstructor
public class ErrorDto {
    /**
     * Сообщение об ошибке.
     */
    private final String error;
}
