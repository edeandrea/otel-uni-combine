package com.example;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import io.smallrye.mutiny.Uni;

@RegisterRestClient(configKey = "apple-client")
@Path("/apple")
public interface AppleClient {
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	Uni<String> getApple();
}
