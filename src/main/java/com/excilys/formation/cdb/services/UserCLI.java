package com.excilys.formation.cdb.services;

import java.sql.SQLException;
import java.util.ArrayList;

import com.excilys.formation.cdb.daos.CompanyDAO;
import com.excilys.formation.cdb.daos.ComputerDAO;
import com.excilys.formation.cdb.daos.DAOFactory;
import com.excilys.formation.cdb.pojos.Company;
import com.excilys.formation.cdb.pojos.Computer;

public class UserCLI {

	private static UserCLI instance;
	private static ComputerDAO computerDAO;
	private static CompanyDAO companyDAO;

	private UserCLI() {
		computerDAO = DAOFactory.getComputerDAO();
		companyDAO = DAOFactory.getCompanyDAO();
	}

	public static UserCLI getInstance() {
		if(instance == null) {
			instance = new UserCLI();
		}
		return instance;
	}

	public String listComputers() {
		ArrayList<Computer> list = computerDAO.list();
		StringBuilder str = new StringBuilder(); 

		for(Object obj : list) {
			str.append(obj.toString() +"\n");
		}
		return str.toString();
	}

	public String listCompanies() {
		ArrayList<Company> list = companyDAO.list();
		StringBuilder str = new StringBuilder(); 

		for(Object obj : list) {
			str.append(obj.toString() +"\n");
		}
		return str.toString();
	}

	public String listComputersByPage(int offset, int rows) {
		ArrayList<Computer> list = computerDAO.listByPage(offset, rows);
		StringBuilder str = new StringBuilder(); 

		for(Object obj : list) {
			str.append(obj.toString() +"\n");
		}
		return str.toString();
	}

	public String listCompanyByPage(int offset, int rows) {
		ArrayList<Company> list = companyDAO.listByPage(offset, rows);
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

	public void disconnect() {
		computerDAO.closeConnection();
		companyDAO.closeConnection();
	}
}
