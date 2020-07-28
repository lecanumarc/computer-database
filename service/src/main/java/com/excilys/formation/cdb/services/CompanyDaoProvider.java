package com.excilys.formation.cdb.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.excilys.formation.cdb.daos.CompanyRepository;
import com.excilys.formation.cdb.models.Company;

@Service
@Transactional
public class CompanyDaoProvider {

	public CompanyRepository instanceDAO;

	@Autowired
	public CompanyDaoProvider(CompanyRepository companyDao) {
		this.instanceDAO = companyDao;
	}

	public Company add(Company obj) {
		return instanceDAO.save(obj);
	}

	public Company edit(Company obj) {
		return instanceDAO.save(obj);
	}

	public Optional<Company> findById(Long id) {
		return instanceDAO.findById(id);
	}

	public int getNumberRows() {
		return (int) instanceDAO.count();
	}

	public Page<Company> listByPage(int offset, int rows) {
		return instanceDAO.findAll(PageRequest.of(offset, rows));
	}

	public List<Company> listCompanies() {
		return instanceDAO.findAll();
	}

}
