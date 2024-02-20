package ru.itmo.se.soa.lab3.service;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import ru.itmo.se.soa.lab3.model.Organization;

public interface FilteringAndOrderingService {
    Specification<Organization> getSpecificationFromFilter(String filter);
    Sort getSortOrder(String[] sort);

}
