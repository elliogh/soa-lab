package ru.itmo.employeeservicecloud.client;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(value = "/orgmanager", produces = MediaType.APPLICATION_XML_VALUE)
public class ClientController {
    private final Service service;

    public ClientController(Service service) {
        this.service = service;
    }

    @DeleteMapping(value = "/fire/all/{id}")
    public ResponseEntity<?> fireAll(@PathVariable("id") Long id) {
        var organization = service.fire(id);
        return ResponseEntity.status(200).body(organization);
    }

    @PostMapping("/hire/{id}")
    public ResponseEntity<?> hire(@PathVariable("id") Long id) {
        var organization = service.hire(id);
        return ResponseEntity.status(200).body(organization);
    }

}
