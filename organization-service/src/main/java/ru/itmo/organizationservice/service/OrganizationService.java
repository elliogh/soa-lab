package ru.itmo.organizationservice.service;

import ru.itmo.organizationservice.dto.OrganizationsList;
import ru.itmo.organizationservice.model.Organization;

public interface OrganizationService {

    OrganizationsList getAllOrganizations(String[] sort, String filter, Integer page, Integer pageSize) throws Exception;
    Organization create(Organization organization) throws Exception;
    Organization getById(Long id) throws Exception;
    Organization updateById(Long id, Organization organization) throws Exception;
    void deleteById(Long id) throws Exception;
    Organization getByMinimalPostalAddress() throws Exception;
    Organization getByMaximumEmployeesCount() throws Exception;
    OrganizationsList getByLessThanFullName(String fullName) throws Exception;

}
