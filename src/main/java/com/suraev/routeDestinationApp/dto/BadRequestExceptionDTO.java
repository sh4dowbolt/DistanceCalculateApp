package com.suraev.routeDestinationApp.dto;

import java.sql.Timestamp;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class BadRequestExceptionDTO {
    private Timestamp timestamp;
    private HttpStatus status;
    private String message;
    private String path;
}
