package com.excilys.formation.cdb.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.excilys.formation.cdb.daos.ComputerDAO;
import com.excilys.formation.cdb.pojos.Computer;

@Service
public class ComputerDaoProvider {

	@Autowired
	public ComputerDAO instanceDAO;

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

	public int getNumberRowsFiltered(String filter) {
		return instanceDAO.getNumberRowsFiltered(filter);
	}

	public ArrayList<Computer> listByPage(int offset, int rows) {
		return instanceDAO.listByPage(offset, rows);
	}

	public ArrayList<Computer> listOrderedAndFiltered(int offset, int rows, String filter, String column, boolean ascOrder) {
		return instanceDAO.listOrderedAndFiltered(offset, rows, filter, column, ascOrder);
	}


	public ArrayList<Computer> listOrdered(int offset, int rows, String column, boolean ascOrder) {
		return instanceDAO.listOrdered(offset, rows, column, ascOrder);
	}

	public ArrayList<Computer> listFiltered(int offset, int rows, String filter) {
		return instanceDAO.listFiltered(offset, rows, filter);
	}

}
