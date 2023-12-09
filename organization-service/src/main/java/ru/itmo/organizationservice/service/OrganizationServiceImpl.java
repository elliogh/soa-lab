package ru.itmo.organizationservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.itmo.organizationservice.dto.OrganizationsList;
import ru.itmo.organizationservice.exception.InvalidParametersException;
import ru.itmo.organizationservice.exception.NotFoundException;
import ru.itmo.organizationservice.model.Organization;
import ru.itmo.organizationservice.repository.OrganizationRepository;
import ru.itmo.organizationservice.util.Validator;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrganizationServiceImpl implements OrganizationService {
    private final Validator validator;
    private final FilteringAndOrderingServiceImpl filteringAndOrderingService;
    private final OrganizationRepository repository;

    @Override
    public OrganizationsList getAllOrganizations(String[] sort, String filter, Integer page, Integer pageSize) throws Exception {
        Specification<Organization> specification = filteringAndOrderingService.getSpecificationFromFilter(filter);
        PageRequest pageRequest = PageRequest.of(page - 1, pageSize, filteringAndOrderingService.getSortOrder(sort));
        List<Organization> organizations = repository.findAll(specification, pageRequest).getContent();
//        List<Organization> organizations = repository.findAll(specification, filteringAndOrderingService.getSortOrder(sort));
        if (organizations.isEmpty()) throw new NotFoundException();
        return new OrganizationsList(organizations);
    }

    @Override
    public Organization create(Organization organization) throws Exception {
        validator.validateOrganization(organization);
        checkWithFullName(organization.getFullName());
        organization.setId(null);
        organization.setCreationDate(LocalDateTime.now().atZone(ZoneId.of("Europe/Moscow")));
        repository.save(organization);
        return organization;
    }

    @Override
    public Organization getById(Long id) throws Exception {
        return repository.findById(id)
                .orElseThrow(NotFoundException::new);
    }

    @Override
    public Organization updateById(Long id, Organization organization) throws Exception {
        validator.validateOrganization(organization);
        Organization org = getById(id);
        organization.setId(org.getId());
        organization.setCreationDate(org.getCreationDate());
        organization.getCoordinates().setId(org.getCoordinates().getId());
        organization.getPostalAddress().setId(org.getPostalAddress().getId());
        repository.save(organization);
        return organization;
    }

    @Override
    public void deleteById(Long id) throws Exception {
        repository.deleteById(id);
    }

    @Override
    public Organization getByMinimalPostalAddress() throws Exception {
        return repository.findFirstByOrderByPostalAddress_ZipCodeAsc()
                .orElseThrow(NotFoundException::new);
    }

    @Override
    public Organization getByMaximumEmployeesCount() throws Exception {
        return repository.findFirstByOrderByEmployeesCountDesc()
                .orElseThrow(NotFoundException::new);
    }

    @Override
    public OrganizationsList getByLessThanFullName(String fullName) throws Exception {
        List<Organization> organizations = repository.findAllByFullNameLessThan(fullName)
                .stream()
                .filter(org -> org.getFullName().length() < fullName.length())
                .collect(Collectors.toList());

        if (organizations.isEmpty()) throw new NotFoundException();
        return new OrganizationsList(organizations);
    }

    private void checkWithFullName(String fullName) throws InvalidParametersException {
        Optional<Organization> o = repository.findByFullName(fullName);
        if (o.isPresent()) throw new InvalidParametersException();
    }
}
