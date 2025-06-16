package com.suraev.routeDestinationApp.exception;

import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.http.ResponseEntity;
import com.suraev.routeDestinationApp.dto.ExceptionResponseDTO;
import com.suraev.routeDestinationApp.dto.BadRequestExceptionDTO;
import org.springframework.http.HttpStatus;

@RestControllerAdvice
public class ExceptionHandlerTranslator {

    @ExceptionHandler(ExceptionResponse.class)
    public ResponseEntity<ExceptionResponseDTO> handleExceptionResponse(ExceptionResponse e) {
        return ResponseEntity
            .status(HttpStatus.valueOf(e.getStatus().intValue()))
            .body(new ExceptionResponseDTO(e.getTimestamp(), e.getStatus(), e.getError(), e.getMessage(), e.getPath()));
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<BadRequestExceptionDTO> handleBadRequestException(BadRequestException e) {
        return ResponseEntity
            .status(e.getStatus())
            .body(new BadRequestExceptionDTO(e.getTimestamp(), e.getStatus(), e.getMessage(), e.getPath()));
    }
}
