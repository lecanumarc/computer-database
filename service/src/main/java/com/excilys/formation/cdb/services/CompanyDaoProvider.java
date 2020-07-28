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
	public CompanyRepository companyDao;

	@Autowired
	public CompanyDaoProvider(CompanyRepository companyDao) {
		this.companyDao = companyDao;
	}

	public void add(Company obj) {
		companyDao.save(obj);
	}

	public void edit(Company obj) {
		companyDao.save(obj);
	}

	public void delete(long id) {
		companyDao.deleteById(id);
	}

	public Optional<Company> findById(Long id) {
		return companyDao.findById(id);
	}

	public int getNumberRows() {
		return (int) companyDao.count();
	}

	public Page<Company> listByPage(int offset, int rows) {
		return companyDao.findAll(PageRequest.of(offset, rows));
	}

	public List<Company> listCompanies() {
		return companyDao.findAll();
	}

}
