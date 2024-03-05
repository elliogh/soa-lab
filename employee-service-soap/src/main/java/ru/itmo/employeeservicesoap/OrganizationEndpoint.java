package ru.itmo.employeeservicesoap;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import ru.itmo.employeeservicesoap.client.RestClient;
import ru.itmo.employeeservicesoap.gen.*;

import java.util.function.Function;

import static ru.itmo.employeeservicesoap.config.WebServiceConfig.NAMESPACE_URI;

@Endpoint
public class OrganizationEndpoint {
    private final RestClient restClient;

    public OrganizationEndpoint(RestClient restClient) {
        this.restClient = restClient;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "fireRequest")
    @ResponsePayload
    public FireResponse fire(@RequestPayload FireRequest request) {
        return (FireResponse) perform(new FireResponse(), restClient::fire, request.getId());
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "hireRequest")
    @ResponsePayload
    public HireResponse hire(@RequestPayload HireRequest request) {
        return (HireResponse) perform(new HireResponse(), restClient::hire, request.getId());
    }

    private Response perform(Response response, Function<Long, Organization> restClientMethod, Long id) {
        var organization = restClientMethod.apply(id);
        response.setOrganization(organization);
        return response;
    }

}
