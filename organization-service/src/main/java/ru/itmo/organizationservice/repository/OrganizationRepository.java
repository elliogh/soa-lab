package ru.itmo.organizationservice.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.itmo.organizationservice.model.Organization;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrganizationRepository extends JpaRepository<Organization, Long>, JpaSpecificationExecutor<Organization> {

    Optional<Organization> findByFullName(String fullName);

    Optional<Organization> findFirstByOrderByPostalAddress_ZipCodeAsc();

    Optional<Organization> findFirstByOrderByEmployeesCountDesc();

    List<Organization> findAllByFullNameLessThan(String fullName);

//    @Query("FROM Organization as o")
//    Page<Organization> findAll(Specification<Organization> spec, Pageable pageable);
}
