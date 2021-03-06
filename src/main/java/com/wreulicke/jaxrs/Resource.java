package com.wreulicke.jaxrs;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.MediaType;

import com.wreulicke.jaxrs.service.UserRepository;

@Path("/test")
public class Resource {
    @Inject
    UserRepository repository;
    
    @GET
    @Path("/user/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public void findUser(@Suspended AsyncResponse response, @PathParam("id") String user) {
      repository.find(user).thenAcceptAsync(response::resume);
    }

}