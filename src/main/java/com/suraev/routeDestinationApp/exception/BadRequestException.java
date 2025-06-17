package com.suraev.routeDestinationApp.exception;

import java.sql.Timestamp;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class BadRequestException extends Exception {
    private Timestamp timestamp;
    private HttpStatus status;
    private String message;
    private String path;

    public BadRequestException(String message, String path) {
        super();
        this.timestamp = new Timestamp(System.currentTimeMillis());
        this.status = HttpStatus.BAD_REQUEST;
        this.message = message;
        this.path = path;
    }
    
    
}
