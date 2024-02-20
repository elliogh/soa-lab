package ru.itmo.se.soa.lab3.service;

import jakarta.ejb.Remote;
import ru.itmo.se.soa.lab3.dto.OrganizationsList;
import ru.itmo.se.soa.lab3.model.Organization;

@Remote
public interface OrganizationService {
    OrganizationsList getAllOrganizations(String[] sort, String filter, Integer page, Integer pageSize) throws Exception;

    Organization create(Organization organization);

    Organization getById(Long id);

    Organization updateById(Long id, Organization organization);

    void deleteById(Long id);

    Organization getByMinimalPostalAddress();

    Organization getByMaximumEmployeesCount();

    OrganizationsList getByLessThanFullName(String fullName);
}
