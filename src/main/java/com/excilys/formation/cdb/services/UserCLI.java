package com.excilys.formation.cdb.services;

import java.sql.SQLException;
import java.util.List;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.excilys.formation.cdb.daos.CompanyDAO;
import com.excilys.formation.cdb.daos.ComputerDAO;
import com.excilys.formation.cdb.pojos.Company;
import com.excilys.formation.cdb.pojos.Computer;

@Component
public class UserCLI {

	private static UserCLI instance;
	@Autowired
	private ComputerDAO computerDAO;
	@Autowired
	private  CompanyDAO companyDAO;

	private UserCLI() {
	}

	public static UserCLI getInstance() {
		if(instance == null) {
			instance = new UserCLI();
		}
		return instance;
	}

	public String listComputers() {
		List<Computer> list = computerDAO.list();
		StringBuilder str = new StringBuilder(); 

		for(Object obj : list) {
			str.append(obj.toString() +"\n");
		}
		return str.toString();
	}

	public String listCompanies() {
		List<Company> list = companyDAO.list();
		StringBuilder str = new StringBuilder(); 

		for(Object obj : list) {
			str.append(obj.toString() +"\n");
		}
		return str.toString();
	}

	public String listComputersByPage(int offset, int rows) {
		List<Computer> list = computerDAO.listByPage(offset, rows);
		StringBuilder str = new StringBuilder(); 

		for(Object obj : list) {
			str.append(obj.toString() +"\n");
		}
		return str.toString();
	}

	public String listCompanyByPage(int offset, int rows) {
		List<Company> list = companyDAO.listByPage(offset, rows);
		StringBuilder str = new StringBuilder(); 

		for(Object obj : list) {
			str.append(obj.toString() +"\n");
		}
		return str.toString();
	}

	public String showComputerDetails(Long id) {
		Computer computer = computerDAO.findById(id);
		return computer.toString();
	}

	public void createComputer(Computer computer) {
		computerDAO.create(computer);
	}

	public void updateComputer(Computer computer) {
		computerDAO.update(computer);
	}

	public void deleteComputer(Long id) {
		computerDAO.delete(id);
	}
	
	public void deleteCompany(Long id) throws SQLException {
		companyDAO.delete(id);
	}

	public int getComputerRows() {
		return computerDAO.getNumberRows();
	}

	public int getCompanyRows() {
		return companyDAO.getNumberRows();
	}
}
