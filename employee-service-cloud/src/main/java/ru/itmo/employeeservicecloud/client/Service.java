package ru.itmo.employeeservicecloud.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@FeignClient(value = "orgservice", url = "http://localhost:8080/organization-service/employees/")
public interface Service {

    @DeleteMapping(value = "/{id}", consumes = "application/xml")
    String fire(@PathVariable("id") Long id);

    @PostMapping(value = "/{id}", consumes = "application/xml")
    String hire(@PathVariable("id") Long id);
}
