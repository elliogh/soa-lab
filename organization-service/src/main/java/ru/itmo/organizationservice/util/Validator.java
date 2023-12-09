package ru.itmo.organizationservice.util;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import ru.itmo.organizationservice.exception.InvalidParametersException;
import ru.itmo.organizationservice.model.Address;
import ru.itmo.organizationservice.model.Coordinates;
import ru.itmo.organizationservice.model.Organization;

import java.util.Objects;

@Service
public class Validator {
    public void validateOrganization(Organization o) throws InvalidParametersException {
        if (StringUtils.isBlank(o.getName())) throwException();
        Coordinates coordinates = o.getCoordinates();
        if (Objects.isNull(coordinates)) throwException();
        if (coordinates.getX() < -631) throwException();
        if (Objects.isNull(coordinates.getY())) throwException();
        if (Objects.nonNull(o.getAnnualTurnover())) {
            if (o.getAnnualTurnover() <= 0) throwException();
        }
        if (Objects.isNull(o.getEmployeesCount()) || o.getEmployeesCount() < 0) throwException();
        Address address = o.getPostalAddress();
        if (StringUtils.isBlank(address.getStreet())) throwException();
        if (StringUtils.isBlank(address.getZipCode())) throwException();
    }

    private void throwException() throws InvalidParametersException {
        throw new InvalidParametersException();
    }
}
