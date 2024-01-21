package ru.itmo.employeeservicecloud.exception;

import org.springframework.http.HttpStatus;

public class InternalServerException extends CustomException {
    public InternalServerException(String message) {
        super(HttpStatus.INTERNAL_SERVER_ERROR, "internal_server_error", message);
    }
}
