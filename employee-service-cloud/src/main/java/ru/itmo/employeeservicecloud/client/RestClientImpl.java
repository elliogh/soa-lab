package ru.itmo.employeeservicecloud.client;

import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RestClientImpl implements RestClient {
    private final RestTemplate restTemplate;
    private final static String URL = "http://organization-service/organization-service/employees/";

    public RestClientImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public String fire(Long id) {
        return restTemplate
                .exchange(URL + id, HttpMethod.DELETE, null, String.class)
                .getBody();
    }

    @Override
    public String hire(Long id) {
        return restTemplate
                .exchange(URL + id, HttpMethod.POST, null, String.class)
                .getBody();
    }
}
