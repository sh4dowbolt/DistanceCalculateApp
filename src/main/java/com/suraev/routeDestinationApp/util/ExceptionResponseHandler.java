package com.suraev.routeDestinationApp.util;

import org.springframework.http.client.ClientHttpResponse;
import java.io.IOException;
import java.io.InputStream;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import com.suraev.routeDestinationApp.dto.ExceptionResponse;
import org.springframework.web.bind.annotation.ResponseStatus;

public class ExceptionResponseHandler {

    public static void handleStatus(ClientHttpResponse response, ObjectMapper objectMapper) throws ExceptionResponse {
        try {
            InputStream inputStream = response.getBody();
            ExceptionResponse exceptionResponse = objectMapper
            .readValue(inputStream, ExceptionResponse.class);
                throw exceptionResponse;
        } catch (IOException e) {
            throw new RuntimeException("Error reading response body", e);
        }
    }

}