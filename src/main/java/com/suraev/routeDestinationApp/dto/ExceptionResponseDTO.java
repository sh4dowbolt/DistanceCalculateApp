package com.suraev.routeDestinationApp.dto;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ExceptionResponseDTO {
    private Timestamp timestamp;
    private Long status;
    private String error;
    private String message;
    private String path;
}
