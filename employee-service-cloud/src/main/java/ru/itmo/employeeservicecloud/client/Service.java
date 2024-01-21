package ru.itmo.employeeservicecloud.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(value = "orgservice", url = "http://localhost:8084/employees/")
public interface Service {

    @DeleteMapping(value = "/{id}", consumes = "application/xml")
    String fire(@PathVariable("id") Long id);
}
