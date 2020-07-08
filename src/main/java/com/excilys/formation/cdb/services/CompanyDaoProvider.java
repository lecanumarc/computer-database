package com.excilys.formation.cdb.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.excilys.formation.cdb.daos.CompanyDAO;
import com.excilys.formation.cdb.pojos.Company;

@Service
public class CompanyDaoProvider {

	@Autowired
	public CompanyDAO instanceDAO;

	public boolean add(Company obj) {
		return instanceDAO.create(obj);
	}

	public boolean edit(Company obj) {
		return instanceDAO.update(obj);
	}

	public Company findById(Long id) {
		return instanceDAO.findById(id);
	}

	public int getNumberRows() {
		return instanceDAO.getNumberRows();
	}

	public ArrayList<Company> listByPage(int offset, int rows) {
		return instanceDAO.listByPage(offset, rows);
	}

	public ArrayList<Company> listCompanies() {
		return instanceDAO.list();
	}

}
