package ru.itmo.organizationservice.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import ru.itmo.organizationservice.dto.BadResponse;
import ru.itmo.organizationservice.exception.CustomException;
import ru.itmo.organizationservice.exception.InvalidParametersException;
import ru.itmo.organizationservice.exception.NotFoundException;
import ru.itmo.organizationservice.model.Organization;
import ru.itmo.organizationservice.service.OrganizationService;

@RestController
@RequestMapping(path = "/employees")
@Slf4j
@RequiredArgsConstructor
public class EmployeeController {
    private final OrganizationService service;

    @DeleteMapping("/{id}")
    public ResponseEntity<?> fireAll(@PathVariable Long id) throws Exception {
        Organization organization = service.getById(id);
        organization.setEmployeesCount(0);
        service.updateById(id, organization);
        return ResponseEntity.ok(organization);
    }

    @PostMapping("/{id}")
    public ResponseEntity<?> hire(@PathVariable Long id) throws Exception {
        Organization organization = service.getById(id);
        organization.setEmployeesCount(organization.getEmployeesCount() + 1);
        service.updateById(id, organization);
        return ResponseEntity.ok(organization);
    }

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<BadResponse> handleException(CustomException e) {
        return ResponseEntity
                .status(e.getStatus())
                .body(buildBadResponse(e.getCode(), e.getMessage()));
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<BadResponse> handleException(MethodArgumentTypeMismatchException e) {
        InvalidParametersException exception = new InvalidParametersException();
        return ResponseEntity
                .status(exception.getStatus())
                .body(buildBadResponse(exception.getCode(), exception.getMessage()));
    }

    @ExceptionHandler(EmptyResultDataAccessException.class)
    public ResponseEntity<BadResponse> handleException(EmptyResultDataAccessException e) {
        NotFoundException exception = new NotFoundException();
        return ResponseEntity
                .status(exception.getStatus())
                .body(buildBadResponse(exception.getCode(), exception.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<BadResponse> handleException(Exception e) {
        log.error(e.getMessage());
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(buildBadResponse("internal_server_error", e.getMessage()));
    }

    private BadResponse buildBadResponse(String code, String message) {
        return BadResponse.builder()
                .code(code)
                .message(message)
                .build();
    }
}
