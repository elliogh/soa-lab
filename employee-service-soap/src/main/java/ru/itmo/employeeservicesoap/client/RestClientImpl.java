package ru.itmo.employeeservicesoap.client;

import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.itmo.employeeservicesoap.gen.Organization;

@Service
public class RestClientImpl implements RestClient {
    private final RestTemplate restTemplate;
    private final static String URL = "http://localhost:8080/organization-service/employees/";

    public RestClientImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public Organization fire(Long id) {
        return restTemplate
                .exchange(URL + id, HttpMethod.DELETE, null, Organization.class)
                .getBody();
    }

    @Override
    public Organization hire(Long id) {
        return restTemplate
                .exchange(URL + id, HttpMethod.POST, null, Organization.class)
                .getBody();
    }
}
