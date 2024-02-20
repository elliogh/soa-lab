package ru.itmo.se.soa.lab3.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import ru.itmo.se.soa.lab3.dto.BadResponse;
import ru.itmo.se.soa.lab3.dto.OrganizationsList;
import ru.itmo.se.soa.lab3.exception.CustomException;
import ru.itmo.se.soa.lab3.exception.InvalidParametersException;
import ru.itmo.se.soa.lab3.exception.NotFoundException;
import ru.itmo.se.soa.lab3.model.Organization;
import ru.itmo.se.soa.lab3.service.OrganizationService;

import java.util.Objects;

@Lazy
@CrossOrigin(origins = "*")
@RestController
@RequestMapping(path = "/organizations")
public class OrganizationController {
    @Autowired
    OrganizationService service;

    @GetMapping(produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<OrganizationsList> getAll(
            @RequestParam(required = false, defaultValue = "id") String[] sort,
            @RequestParam(required = false) String filter,
            @RequestParam(required = false, defaultValue = "1") Integer page,
            @RequestParam(required = false, defaultValue = "10") Integer pageSize
    ) throws Exception {
        OrganizationsList organization = service.getAllOrganizations(sort, filter, page, pageSize);
        if (Objects.isNull(organization)) throw new NotFoundException();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(organization);
    }

    @PostMapping(produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<Organization> create(@RequestBody Organization organization) throws Exception {
        Organization org = service.create(organization);
        if (Objects.isNull(org)) throw new InvalidParametersException();
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(org);
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<Organization> getById(@PathVariable Long id) throws Exception {
        Organization organization = service.getById(id);
        if (Objects.isNull(organization)) throw new NotFoundException();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(organization);
    }

    @PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<Organization> updateById(@PathVariable Long id, @RequestBody Organization organization) throws Exception {
        Organization org = service.updateById(id, organization);
        if (Objects.isNull(org)) throw new InvalidParametersException();
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
        Organization organization = service.getByMinimalPostalAddress();
        if (Objects.isNull(organization)) throw new NotFoundException();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(organization);
    }

    @GetMapping(path = "/maximum-employees-count", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<Organization> getByMaximumEmployeesCount() throws Exception {
        Organization organization = service.getByMaximumEmployeesCount();
        if (Objects.isNull(organization)) throw new NotFoundException();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(organization);
    }

    @GetMapping(path = "/less-than-fullname", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<OrganizationsList> getByLessThanFullName(@RequestParam("fullname") String fullName) throws Exception {
        OrganizationsList organizationsList = service.getByLessThanFullName(fullName);
        if (Objects.isNull(organizationsList)) throw new NotFoundException();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(organizationsList);
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
        e.printStackTrace();
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
