package com.excilys.formation.cdb.test.pojos;

import static org.junit.Assert.assertEquals;
import java.time.LocalDate;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.excilys.formation.cdb.exceptions.ComputerValidatorException;
import com.excilys.formation.cdb.pojos.Computer;
import com.excilys.formation.cdb.pojos.Computer.ComputerBuilder;

public class TestComputer {
	Computer computer;

	@Before
	public void setUp() throws Exception {
		computer = null;
	}
	
	@Rule
	public ExpectedException exception = ExpectedException.none();

//	@Test
//	public void testRodneCisloRok(){
//	    exception.expect(IllegalArgumentException.class);
//	    exception.expectMessage("error1");
//	    new RodneCislo("891415",dopocitej("891415"));
//	}
//	
	@Test
	public void testSetEmptyName() {
		exception.expect(ComputerValidatorException.class);
		computer = new ComputerBuilder("").build();
	}
	
}
