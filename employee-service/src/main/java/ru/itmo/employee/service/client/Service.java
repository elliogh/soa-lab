package ru.itmo.employee.service.client;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@RegisterRestClient
@ApplicationScoped
public interface Service {

    @DELETE
    @Path("/{id}")
    @Produces(value = MediaType.APPLICATION_XML)
    @Consumes(value = MediaType.APPLICATION_XML)
    Response fire(@PathParam("id") Long id);

    @POST
    @Path("/{id}")
    @Produces(value = MediaType.APPLICATION_XML)
    @Consumes(value = MediaType.APPLICATION_XML)
    Response hire(@PathParam("id") Long id);
}
