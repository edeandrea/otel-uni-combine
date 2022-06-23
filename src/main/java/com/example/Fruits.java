package com.example;

public class Fruits {
	private Fruit fruit1;
	private Fruit fruit2;

	public Fruits(Fruit fruit1, Fruit fruit2) {
		this.fruit1 = fruit1;
		this.fruit2 = fruit2;
	}

	public Fruits() {
	}

	public Fruit getFruit1() {
		return fruit1;
	}

	public void setFruit1(Fruit fruit1) {
		this.fruit1 = fruit1;
	}

	public Fruit getFruit2() {
		return fruit2;
	}

	public void setFruit2(Fruit fruit2) {
		this.fruit2 = fruit2;
	}

	@Override
	public String toString() {
		return "Fruits{" + "fruit1=" + fruit1 + ", fruit2=" + fruit2 + '}';
	}
}
