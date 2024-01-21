package ru.itmo.employeeservicecloud.exception.handler;

import feign.FeignException;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ru.itmo.employeeservicecloud.dto.BadResponse;
import ru.itmo.employeeservicecloud.exception.CustomException;
import ru.itmo.employeeservicecloud.exception.InternalServerException;
import ru.itmo.employeeservicecloud.exception.InvalidParametersException;
import ru.itmo.employeeservicecloud.exception.NotFoundException;

@ControllerAdvice
public class ErrorHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = FeignException.NotFound.class)
    protected ResponseEntity<BadResponse> handleConflict(FeignException.NotFound e) {
        return buildResponseEntity(new NotFoundException());
    }

    @ExceptionHandler(value = MethodArgumentTypeMismatchException.class)
    protected ResponseEntity<BadResponse> handleConflict(MethodArgumentTypeMismatchException e) {
        return buildResponseEntity(new InvalidParametersException());
    }

    @ExceptionHandler(value = Exception.class)
    protected ResponseEntity<BadResponse> handleConflict(Exception e) {
        return buildResponseEntity(new InternalServerException(e.getMessage()));
    }

    private ResponseEntity<BadResponse> buildResponseEntity(CustomException e) {
        return ResponseEntity
                .status(e.getStatus())
                .contentType(MediaType.APPLICATION_XML)
                .body(buildBadResponse(e.getCode(), e.getMessage()));
    }

    private BadResponse buildBadResponse(String code, String message) {
        return BadResponse.builder()
                .code(code)
                .message(message)
                .build();
    }
}
