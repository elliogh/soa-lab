package ru.itmo.employeeservicesoap.client;


import ru.itmo.employeeservicesoap.gen.Organization;

public interface RestClient {
    Organization fire(Long id);

    Organization hire(Long id);
}
