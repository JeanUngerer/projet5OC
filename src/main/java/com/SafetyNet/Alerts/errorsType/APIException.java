package com.SafetyNet.Alerts.errorsType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;


@Getter
@AllArgsConstructor
public class APIException {
    private final String message;
    private final Throwable throwable;
    private final HttpStatus httpStatus;
    private final ZonedDateTime timeStamp;
}