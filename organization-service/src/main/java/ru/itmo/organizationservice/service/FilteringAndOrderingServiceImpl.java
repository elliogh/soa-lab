package ru.itmo.organizationservice.service;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.query.criteria.internal.path.RootImpl;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.itmo.organizationservice.model.Address;
import ru.itmo.organizationservice.model.Coordinates;
import ru.itmo.organizationservice.model.Organization;
import ru.itmo.organizationservice.model.OrganizationType;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class FilteringAndOrderingServiceImpl implements FilteringAndOrderingService {

    @Override
    public Specification<Organization> getSpecificationFromFilter(String filter) {
        if (StringUtils.isBlank(filter)) return null;
        Map<String, Object> map = getMapOfPredicates(filter);
        return (root, query, criteriaBuilder) -> {

            Join<Organization, Coordinates> coordinatesJoin = root.join("coordinates");
            Join<Organization, Address> addressJoin = root.join("postalAddress");
            Predicate[] predicates = map.entrySet().stream()
                    .map(entry -> {
                        String field = entry.getKey();
                        if (field.equals("x") || field.equals("y")) {
                            return criteriaBuilder.equal(coordinatesJoin.get(entry.getKey()), entry.getValue());
                        }
                        if (field.equals("street") || field.equals("zipCode")) {
                            return criteriaBuilder.equal(addressJoin.get(entry.getKey()), entry.getValue());
                        }
                        return criteriaBuilder.equal(root.get(entry.getKey()), entry.getValue());
                    })
//                    .map(entry -> criteriaBuilder
//                            .equal(first.get(entry.getKey()),
//                                    entry.getValue()))
                    .collect(Collectors.toList())
                    .toArray(new Predicate[]{});
            return criteriaBuilder.and(predicates);
        };
    }

    @Override
    public Sort getSortOrder(String[] sort) {
        return Sort.by(
                Arrays.stream(sort)
                        .map(this::makeSortOrder)
                        .collect(Collectors.toList())
        );
    }

    private Map<String, Object> getMapOfPredicates(String filter) {
        return Arrays.stream(filter.split(";"))
                .map(predicate -> predicate.split("="))
                .collect(Collectors.toMap(
                        p -> p[0],
                        p -> mapType(p[1]))
                );
    }

    private Object mapType(String value) {
        try {
            return OrganizationType.valueOf(value);
        } catch (Exception e) {
        }

        return value;
    }

    private Sort.Order makeSortOrder(String fieldName) {
        return new Sort.Order(getSortDirection(fieldName), removeExcess(fieldName));
    }

    private Sort.Direction getSortDirection(String sortDirection) {
        return sortDirection.contains("-") ? Sort.Direction.DESC
                : Sort.Direction.ASC;
    }

    private String removeExcess(String fieldName) {
        return fieldName.replaceAll("-", "");
    }
}
