package ru.itmo.se.soa.lab3.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import ru.itmo.se.soa.lab3.dto.BadResponse;
import ru.itmo.se.soa.lab3.exception.CustomException;
import ru.itmo.se.soa.lab3.exception.InvalidParametersException;
import ru.itmo.se.soa.lab3.exception.NotFoundException;
import ru.itmo.se.soa.lab3.model.Organization;
import ru.itmo.se.soa.lab3.service.OrganizationService;

import java.util.Objects;

@Lazy
@RestController
@RequestMapping(path = "/employees")
public class EmployeeController {
    @Autowired
    OrganizationService service;

    @DeleteMapping("/{id}")
    public ResponseEntity<?> fireAll(@PathVariable Long id) throws Exception {
        Organization organization = service.getById(id);
        if (Objects.isNull(organization)) throw new NotFoundException();
        organization.setEmployeesCount(0);
        service.updateById(id, organization);
        return ResponseEntity.ok(organization);
    }

    @PostMapping("/{id}")
    public ResponseEntity<?> hire(@PathVariable Long id) throws Exception {
        Organization organization = service.getById(id);
        if (Objects.isNull(organization)) throw new NotFoundException();
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


    @ExceptionHandler(Exception.class)
    public ResponseEntity<BadResponse> handleException(Exception e) {
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
