package com.excilys.formation.cdb.validator;

import com.excilys.formation.cdb.exception.ComputerValidatorException;
import com.excilys.formation.cdb.model.Computer;

public class ComputerValidator {

	public static void validate(Computer computer) {
		if(computer == null) {
			throw new ComputerValidatorException ("Computer object is null");
		}
		if(computer.getName() == null || computer.getName().isEmpty()) {
			throw new ComputerValidatorException("Name cannot be empty");
		}
		if(computer.getIntroduced() != null && computer.getDiscontinued() != null && computer.getIntroduced().isAfter(computer.getDiscontinued())) {
			throw new ComputerValidatorException("Introduction date must be before discontinuation date.");
		}
		
	}
}
