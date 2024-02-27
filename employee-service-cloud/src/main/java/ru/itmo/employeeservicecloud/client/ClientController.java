package ru.itmo.employeeservicecloud.client;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/orgmanager", produces = MediaType.APPLICATION_XML_VALUE)
public class ClientController {
    private final RestClient restClient;

    public ClientController(RestClient restClient) {
        this.restClient = restClient;
    }

    @DeleteMapping(value = "/fire/all/{id}")
    public ResponseEntity<?> fireAll(@PathVariable("id") Long id) {
        var organization = restClient.fire(id);
        return ResponseEntity.status(200).body(organization);
    }

    @PostMapping("/hire/{id}")
    public ResponseEntity<?> hire(@PathVariable("id") Long id) {
        var organization = restClient.hire(id);
        return ResponseEntity.status(200).body(organization);
    }

}
