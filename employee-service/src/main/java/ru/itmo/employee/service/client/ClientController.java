package ru.itmo.employee.service.client;

import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/orgmanager")
@ApplicationScoped
public class ClientController {

    @Inject
    @RestClient
    private Service service;

    @DELETE
    @Path("/fire/all/{id}")
    @Produces(value = MediaType.APPLICATION_XML)
    public Response fireAll(@PathParam("id") Long id) {
        System.out.println("fire");
        Response response = service.fire(id);
        System.out.println(response);
        return Response.status(response.getStatus()).build();
    }

    @POST
    @Path("/hire/{id}")
    @Produces(value = MediaType.APPLICATION_XML)
    public Response hire(@PathParam("id") Long id) {
        System.out.println("hire");
        Response response = service.hire(id);
        return Response.status(response.getStatus()).build();
    }
}
