package com.suraev.routeDestinationApp.dto;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ExceptionResponse {
    private Timestamp timestamp;
    private Long status;
    private String error;
    private String message;
    private String path;
}
