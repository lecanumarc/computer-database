package com.excilys.formation.cdb.pojos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Car {
	private String model;
	@Autowired
	private Tyre tyre;
	
	public Car() {
		
	}
	
	public Car(String model) {
		super();
		this.model = model;
	}
	
	public Car(String model, Tyre tyre) {
		super();
		this.model = model;
		this.tyre = tyre;
	}

	@Override
	public String toString() {
		return "Car [model=" + model + ", tyre=" + tyre + "]";
	}

	public void drive() {
		System.out.println("Driving ... "+model);
	}
}
