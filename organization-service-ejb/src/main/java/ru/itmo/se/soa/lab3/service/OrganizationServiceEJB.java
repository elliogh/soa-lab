package ru.itmo.se.soa.lab3.service;

import jakarta.annotation.PostConstruct;
import jakarta.ejb.Remote;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;
import org.springframework.data.repository.core.support.RepositoryFactorySupport;
import ru.itmo.se.soa.lab3.dto.OrganizationsList;
import ru.itmo.se.soa.lab3.exception.InvalidParametersException;
import ru.itmo.se.soa.lab3.model.Organization;
import ru.itmo.se.soa.lab3.repository.OrganizationRepository;
import ru.itmo.se.soa.lab3.util.Validator;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Remote(OrganizationService.class)
@Stateless
public class OrganizationServiceEJB implements OrganizationService {
    Validator validator = new Validator();
    FilteringAndOrderingService filteringAndOrderingService = new FilteringAndOrderingServiceImpl();
    @PersistenceContext
    private EntityManager entityManager;
    private OrganizationRepository repository;

    @PostConstruct
    private void init() {
        RepositoryFactorySupport factory = new JpaRepositoryFactory(entityManager);
        this.repository = factory.getRepository(OrganizationRepository.class);
    }

    @Override
    public OrganizationsList getAllOrganizations(String[] sort, String filter, Integer page, Integer pageSize) {
        Specification<Organization> specification = filteringAndOrderingService.getSpecificationFromFilter(filter);
        PageRequest pageRequest = PageRequest.of(page - 1, pageSize, filteringAndOrderingService.getSortOrder(sort));
        List<Organization> organizations = repository.findAll(specification, pageRequest).getContent();
        if (organizations.isEmpty()) return null;
        return new OrganizationsList(organizations);
    }

    @Override
    public Organization create(Organization organization) {
        try {
            validator.validateOrganization(organization);
        } catch (InvalidParametersException e) {
            return null;
        }
        if (checkWithFullName(organization.getFullName())) return null;
        organization.setId(null);
        organization.setCreationDate(LocalDateTime.now().atZone(ZoneId.of("Europe/Moscow")));
        repository.save(organization);
        return organization;
    }

    @Override
    public Organization getById(Long id) {
        return repository.findById(id)
                .orElse(null);
    }

    @Override
    public Organization updateById(Long id, Organization organization) {
        try {
            validator.validateOrganization(organization);
        } catch (InvalidParametersException e) {
            return null;
        }
        Organization org = getById(id);
        if (org == null) return null;
        organization.setId(org.getId());
        organization.setCreationDate(org.getCreationDate());
        organization.getCoordinates().setId(org.getCoordinates().getId());
        organization.getPostalAddress().setId(org.getPostalAddress().getId());
        repository.save(organization);
        return organization;
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Organization getByMinimalPostalAddress() {
        return repository.findFirstByOrderByPostalAddress_ZipCodeAsc()
                .orElse(null);
    }

    @Override
    public Organization getByMaximumEmployeesCount() {
        return repository.findFirstByOrderByEmployeesCountDesc()
                .orElse(null);
    }

    @Override
    public OrganizationsList getByLessThanFullName(String fullName) {
        List<Organization> organizations = repository.findAllByFullNameLessThan(fullName)
                .stream()
                .filter(org -> org.getFullName().length() < fullName.length())
                .collect(Collectors.toList());

        if (organizations.isEmpty()) return null;
        return new OrganizationsList(organizations);
    }

    private boolean checkWithFullName(String fullName) {
        Optional<Organization> o = repository.findByFullName(fullName);
        return o.isPresent();
    }
}
