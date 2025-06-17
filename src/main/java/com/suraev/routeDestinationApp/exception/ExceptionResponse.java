package com.suraev.routeDestinationApp.exception;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ExceptionResponse extends RuntimeException {
    
    private Timestamp timestamp;
    private Long status;
    private String error;
    private String message;
    private String path;

    public ExceptionResponse(Long status, String error, String message, String path) {
        super();
        this.timestamp = new Timestamp(System.currentTimeMillis());
        this.status = status;
        this.error = error;
        this.message = message;
        this.path = path;
    }

}
