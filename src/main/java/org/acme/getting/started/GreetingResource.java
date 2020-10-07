package org.acme.getting.started;

import java.util.concurrent.CompletionStage;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.reactive.streams.operators.ReactiveStreams;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

@Path("/hello")
public class GreetingResource {

    @Inject
    GreetingService service;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/greeting/{name}")
    public String greeting(@PathParam String name) {
        return service.greeting(name);
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "hello";
    }

    @GET
    @Path("/async")
    @Produces(MediaType.TEXT_PLAIN)
    public CompletionStage<String> helloAsync() {
        return ReactiveStreams.of("h", "e", "l", "l", "o")
        .map(s -> s.toUpperCase())
        .toList().run().thenApply(l -> l.toString());
    }
}