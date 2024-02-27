package ru.itmo.employeeservicecloud.client;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.itmo.employeeservicecloud.config.RibbonConfig;


@FeignClient(value = "organization-service", url ="${organization-service.ribbon.listOfServers}")
@RibbonClient(name = "organization-service", configuration = RibbonConfig.class)
public interface Service {

    @DeleteMapping(value = "/organization-service/employees/{id}", consumes = "application/xml")
    String fire(@PathVariable("id") Long id);

    @PostMapping(value = "/organization-service/employees/{id}", consumes = "application/xml")
    String hire(@PathVariable("id") Long id);
}
