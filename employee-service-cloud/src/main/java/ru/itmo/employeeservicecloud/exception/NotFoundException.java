package ru.itmo.employeeservicecloud.exception;

import org.springframework.http.HttpStatus;

public class NotFoundException extends CustomException {
    public NotFoundException() {
        super(HttpStatus.NOT_FOUND, "not_found", "Not found");
    }
}
