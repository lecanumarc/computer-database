package com.excilys.formation.cdb.pojos;

import org.springframework.stereotype.Component;

@Component
public class Tyre {
	String model;
	
	public Tyre() {
	}
	
	public Tyre(String model) {
		super();
		this.model = model;
	}

	public void printModel() {
		System.out.println("Tyre ..."+model);
	}
}
