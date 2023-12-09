package ru.itmo.organizationservice.dto;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.*;
import ru.itmo.organizationservice.model.Organization;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JacksonXmlRootElement(localName = "organizations")

public class OrganizationsList {
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<Organization> organization;
}
