package ru.itmo.employeeservicecloud;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/test")
public class TestController {
    @Value("${test.three}")
    String test;

    @GetMapping
    public ResponseEntity<?> test() {
        return ResponseEntity.ok(test);
    }
}
