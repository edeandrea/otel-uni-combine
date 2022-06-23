package com.example;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import io.quarkus.logging.Log;

import io.smallrye.mutiny.Uni;

@Path("/fruits")
@Produces(MediaType.APPLICATION_JSON)
public class FruitResource {
	private final FruitService fruitService;

	public FruitResource(FruitService fruitService) {
		this.fruitService = fruitService;
	}

	@GET
	public Uni<Fruits> getFruits() {
		Log.debug("Getting fruits");
		return this.fruitService.getFruits();
	}

	@GET
	@Path("/{name}")
	public Uni<Response> getFruit(@PathParam("name") String name) {
		return ("apple".equalsIgnoreCase(name) ? this.fruitService.getApple() :
		       "pear".equalsIgnoreCase(name) ? this.fruitService.getPear() : Uni.createFrom().nullItem())
			.onItem().ifNotNull().transform(fruit -> Response.ok(fruit).build())
			.onItem().ifNull().continueWith(() -> Response.status(Status.NOT_FOUND).build());
	}
}
