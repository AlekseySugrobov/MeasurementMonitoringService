package com.offsidegaming.measuremonitoringservice.in.controller.advice;

import com.offsidegaming.measuremonitoringservice.domain.dto.ErrorDto;
import com.offsidegaming.measuremonitoringservice.exceptions.MeasurementConflictException;
import com.offsidegaming.measuremonitoringservice.exceptions.UnknownMeasurementTypeException;
import com.offsidegaming.measuremonitoringservice.exceptions.UnknownUserIdException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(value = MeasurementConflictException.class)
    public ResponseEntity<ErrorDto> handleMeasurementConflictException(MeasurementConflictException exception) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(this.logAndConvertException(exception));
    }

    @ExceptionHandler(value = UnknownMeasurementTypeException.class)
    public ResponseEntity<ErrorDto> handleUnknownMeasurementTypeException(UnknownMeasurementTypeException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(this.logAndConvertException(exception));
    }

    @ExceptionHandler(value = UnknownUserIdException.class)
    public ResponseEntity<ErrorDto> handleUnknownUserIdException(UnknownUserIdException exception) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(this.logAndConvertException(exception));
    }

    private ErrorDto logAndConvertException(Exception exception) {
        log.error(exception.getMessage(), exception);
        return new ErrorDto(exception.getMessage());
    }
}
