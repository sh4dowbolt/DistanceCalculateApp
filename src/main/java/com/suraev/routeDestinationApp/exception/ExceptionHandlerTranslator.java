package com.suraev.routeDestinationApp.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.http.ResponseEntity;
import com.suraev.routeDestinationApp.dto.ExceptionResponse;
import com.suraev.routeDestinationApp.dto.ExceptionResponseDTO;
import com.suraev.routeDestinationApp.dto.BadRequestException;
import com.suraev.routeDestinationApp.dto.BadRequestExceptionDTO;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.http.HttpStatus;

@ControllerAdvice
public class ExceptionHandlerTranslator {

    @ExceptionHandler(ExceptionResponse.class)
    @ResponseBody
    public ResponseEntity<ExceptionResponseDTO> handleExceptionResponse(ExceptionResponse e) {
        return ResponseEntity
            .status(HttpStatus.valueOf(e.getStatus().intValue()))
            .body(new ExceptionResponseDTO(e.getTimestamp(), e.getStatus(), e.getError(), e.getMessage(), e.getPath()));
    }

    @ExceptionHandler(BadRequestException.class)
    
    @ResponseBody
    public ResponseEntity<BadRequestExceptionDTO> handleBadRequestException(BadRequestException e) {
        return ResponseEntity
            .status(e.getStatus())
            .body(new BadRequestExceptionDTO(e.getTimestamp(), e.getStatus(), e.getMessage(), e.getPath()));
    }
}
