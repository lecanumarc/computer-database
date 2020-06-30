package com.excilys.formation.cdb.validator;

import com.excilys.formation.cdb.exceptions.ComputerValidatorException;
import com.excilys.formation.cdb.pojos.Computer;

public class ComputerValidator {

	public static void validate(Computer computer) {
		if(computer == null) {
			throw new ComputerValidatorException ("Computer object is null");
		}
		if(computer.getName().isEmpty() || computer.getName() == null) {
			throw new ComputerValidatorException("Name cannot be empty");
		}
		if(computer.getintroduced() != null && computer.getdiscontinued() != null && computer.getintroduced().isAfter(computer.getdiscontinued())) {
			throw new ComputerValidatorException("Introduction date must be before discontinuation date.");
		}
		
	}
}
