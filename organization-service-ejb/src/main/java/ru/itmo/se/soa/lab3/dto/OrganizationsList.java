package ru.itmo.se.soa.lab3.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.itmo.se.soa.lab3.model.Organization;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrganizationsList implements Serializable {
    private static final long serialVersionUID = 1L;

    private List<Organization> organization;
}
