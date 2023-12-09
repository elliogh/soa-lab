package ru.itmo.organizationservice.service;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import ru.itmo.organizationservice.model.Organization;

public interface FilteringAndOrderingService {
    Specification<Organization> getSpecificationFromFilter(String filter);
    Sort getSortOrder(String[] sort);

}
