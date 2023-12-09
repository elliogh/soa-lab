package ru.itmo.organizationservice.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import ru.itmo.organizationservice.dto.BadResponse;
import ru.itmo.organizationservice.dto.OrganizationsList;
import ru.itmo.organizationservice.exception.CustomException;
import ru.itmo.organizationservice.exception.InvalidParametersException;
import ru.itmo.organizationservice.exception.NotFoundException;
import ru.itmo.organizationservice.model.Organization;
import ru.itmo.organizationservice.service.OrganizationService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(path = "/organizations")
@Slf4j
@RequiredArgsConstructor
public class OrganizationController {
    private final OrganizationService service;

    @GetMapping(produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<OrganizationsList> getAll(
            @RequestParam(required = false, defaultValue = "id") String[] sort,
            @RequestParam(required = false) String filter,
            @RequestParam(required = false, defaultValue = "1") Integer page,
            @RequestParam(required = false, defaultValue = "10") Integer pageSize
    ) throws Exception {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(service.getAllOrganizations(sort, filter, page, pageSize));
    }

    @PostMapping(produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<Organization> create(@RequestBody Organization organization) throws Exception {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(service.create(organization));
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<Organization> getById(@PathVariable Long id) throws Exception {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(service.getById(id));
    }

    @PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<Organization> updateById(@PathVariable Long id, @RequestBody Organization organization) throws Exception {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(service.updateById(id, organization));
    }

    @DeleteMapping(path = "/{id}", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<String> deleteById(@PathVariable Long id) throws Exception {
        service.deleteById(id);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .body(StringUtils.EMPTY);
    }

    @GetMapping(path = "/minimum-postal-address", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<Organization> getByMinimalPostalAddress() throws Exception {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(service.getByMinimalPostalAddress());
    }

    @GetMapping(path = "/maximum-employees-count", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<Organization> getByMaximumEmployeesCount() throws Exception {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(service.getByMaximumEmployeesCount());
    }

    @GetMapping(path = "/less-than-fullname", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<OrganizationsList> getByLessThanFullName(@RequestParam("fullname") String fullName) throws Exception {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(service.getByLessThanFullName(fullName));
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

    @ExceptionHandler(InvalidDataAccessApiUsageException.class)
    public ResponseEntity<BadResponse> handleException(InvalidDataAccessApiUsageException e) {
        InvalidParametersException exception = new InvalidParametersException();
        return ResponseEntity
                .status(exception.getStatus())
                .body(buildBadResponse(exception.getCode(), exception.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<BadResponse> handleException(Exception e) {
        e.printStackTrace();
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
