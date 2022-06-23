package com.example;

import javax.enterprise.context.ApplicationScoped;

import org.eclipse.microprofile.rest.client.inject.RestClient;

import io.quarkus.logging.Log;

import io.opentelemetry.extension.annotations.WithSpan;
import io.smallrye.mutiny.Uni;

@ApplicationScoped
public class FruitService {
	private final AppleClient appleClient;
	private final PearClient pearClient;

	public FruitService(@RestClient AppleClient appleClient, @RestClient PearClient pearClient) {
		this.appleClient = appleClient;
		this.pearClient = pearClient;
	}

	@WithSpan("FruitsService.getFruits")
	public Uni<Fruits> getFruits() {
		Log.debug("Getting fruits");

		return Uni.combine()
			.all()
			.unis(
				this.appleClient.getApple().map(Fruit::new).onItem().ifNull().continueWith(new Fruit()),
				this.pearClient.getPear().map(Fruit::new).onItem().ifNull().continueWith(new Fruit())
			)
			.combinedWith(Fruits::new);
	}

	@WithSpan("FruitService.getApple")
	public Uni<Fruit> getApple() {
		return this.appleClient.getApple()
			.onItem().ifNotNull().transform(Fruit::new);
	}

	@WithSpan("FruitService.getPear")
	public Uni<Fruit> getPear() {
		return this.pearClient.getPear()
			.onItem().ifNotNull().transform(Fruit::new);
	}
}
