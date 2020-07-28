package com.excilys.formation.cdb.test.daos;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import com.excilys.formation.cdb.configuration.SpringConfig;
import com.excilys.formation.cdb.daos.ComputerDAO;
import com.excilys.formation.cdb.exceptions.ComputerValidatorException;
import com.excilys.formation.cdb.pojos.Company;
import com.excilys.formation.cdb.pojos.Company.CompanyBuilder;
import com.excilys.formation.cdb.pojos.Computer;
import com.excilys.formation.cdb.pojos.Computer.ComputerBuilder;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringConfig.class, loader = AnnotationConfigContextLoader.class)
public class TestComputerDAO {

	@Autowired
	ComputerDAO computerDAO;

	@Rule
	public ExpectedException exception = ExpectedException.none();
	//	Computer creation tests
	@Test
	public void createComputerWithNameOnly() throws Exception {
		Computer computer = new Computer.ComputerBuilder("TestName").setId(new Long(1)).build();
		assertTrue("Computer was not created", computerDAO.create(computer));
	}

	@Test
	public void createComputerWithAttributes() throws Exception {
		Company company = new CompanyBuilder().setId(new Long(1)).build();
		Computer computer = new Computer.ComputerBuilder("TestName")
				.setId(new Long(1))
				.setCompany(company)
				.setIntroduced(LocalDate.now())
				.setDiscontinued(LocalDate.now().plusYears(new Long(1)))
				.build();
		assertTrue("Computer was not created", computerDAO.create(computer));
	}
	
	@Test
	public void createComputerValidatorException() throws Exception {
		exception.expect(ComputerValidatorException.class);
	    exception.expectMessage("Name cannot be empty");
		Computer computer = new Computer.ComputerBuilder("").build();
	}

	@Test
	public void testFindById() {
		Computer computer = computerDAO.findById((long) 17);
		assertEquals("computer id is incorrect", new Long(17), computer.getId());
		assertEquals("computer name is incorrect", "Apple III Plus", computer.getName());
		assertEquals("computer introduction date is incorrect", LocalDate.parse("1983-12-01"), computer.getIntroduced());
		assertEquals("computer discontinuation is incorrect", LocalDate.parse("1984-04-01"), computer.getDiscontinued());
		assertEquals("computer company id is incorrect", new Long(1), computer.getCompany().getId());
	}

	@Test
	public void testFindByName() {
		Computer computer = computerDAO.findByName("Apple III Plus");
		assertEquals("computer id is incorrect", new Long(17), computer.getId());
		assertEquals("computer name is incorrect", "Apple III Plus", computer.getName());
		assertEquals("computer introduction date is incorrect", LocalDate.parse("1983-12-01"), computer.getIntroduced());
		assertEquals("computer discontinuation is incorrect", LocalDate.parse("1984-04-01"), computer.getDiscontinued());
		assertEquals("computer company id is incorrect", new Long(1), computer.getCompany().getId());
	}

	@Test
	public void testDelete() throws Exception {
		boolean deletion = computerDAO.delete((long) 17);
		assertTrue("computer was not deleted", deletion);
	}

	@Test
	public void testUpdate() throws Exception {	
		Computer computer = new Computer.ComputerBuilder("TestName").setId(new Long(17)).build();
		computerDAO.update(computer);
		assertEquals("TestName",computerDAO.findById(new Long(17)).getName());
	}

	@Test
	public void testCountRows() throws Exception {
		assertEquals(574, computerDAO.getNumberRows());
	}

	@Test
	public void testList() throws Exception {
//		ArrayList<Computer> list = computerDAO.list();
//		assertEquals(574, list.size());
	}


	@Test
	public void testListByPage() throws Exception {
		List<Computer> list = computerDAO.listByPage(0, 10);
		assertEquals(10, list.size());
	}

}
