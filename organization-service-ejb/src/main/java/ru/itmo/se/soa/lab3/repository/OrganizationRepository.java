package ru.itmo.se.soa.lab3.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import ru.itmo.se.soa.lab3.model.Organization;

import java.util.List;
import java.util.Optional;

public interface OrganizationRepository extends JpaRepository<Organization, Long>, JpaSpecificationExecutor<Organization> {
    Optional<Organization> findByFullName(String fullName);

    Optional<Organization> findFirstByOrderByPostalAddress_ZipCodeAsc();

    Optional<Organization> findFirstByOrderByEmployeesCountDesc();

    List<Organization> findAllByFullNameLessThan(String fullName);
}
