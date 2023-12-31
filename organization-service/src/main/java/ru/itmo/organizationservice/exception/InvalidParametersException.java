package ru.itmo.organizationservice.exception;

import org.springframework.http.HttpStatus;

public class InvalidParametersException extends CustomException {
    public InvalidParametersException() {
        super(HttpStatus.BAD_REQUEST, "invalid_parameters", "Invalid parameters in request");
    }
}
