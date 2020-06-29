package com.excilys.formation.cdb.services;

import java.util.ArrayList;

import com.excilys.formation.cdb.daos.ComputerDAO;
import com.excilys.formation.cdb.daos.DAOFactory;
import com.excilys.formation.cdb.pojos.Computer;

public class ComputerDaoProvider {

	public static ComputerDAO instanceDAO;
	public static ComputerDaoProvider instance;

	private ComputerDaoProvider() {
		instanceDAO = DAOFactory.getComputerDAO();

	}

	public static ComputerDaoProvider getInstance() {
		if(instance == null) {
			instance = new ComputerDaoProvider();
		}
		return instance;
	}
	
	public boolean add(Computer obj) {
		return instanceDAO.create(obj);
	}

	public boolean delete(Long id) {
		return instanceDAO.delete(id);
	}

	public boolean edit(Computer obj) {
		return instanceDAO.update(obj);
	}

	public Computer findById(Long id) {
		return instanceDAO.findById(id);
	}

	public int getNumberRows() {
		return instanceDAO.getNumberRows();
	}

	public ArrayList<Computer> listByPage(int offset, int rows) {
		return instanceDAO.listByPage(offset, rows);
	}

}
