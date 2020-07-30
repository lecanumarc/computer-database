package com.excilys.formation.cdb.service;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.excilys.formation.cdb.dao.CompanyRepository;
import com.excilys.formation.cdb.model.Company;

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

	public Page<Company> listByPage(int index, int rows) {
		return companyDao.findAll(PageRequest.of(index, rows));
	}
	
	public Page<Company> listOrderedAndFiltered(int index, int rows, String filter, String column, boolean ascOrder) {
		return companyDao.findByNameContaining(filter, PageRequest.of(index, rows , sortBy(column, ascOrder)));
	}

	private Sort sortBy(String column, boolean ascOrder) {
		if(ascOrder) {
			return Sort.by(column).ascending();
		}
		return Sort.by(column).descending();
	}

	public List<Company> listCompanies() {
		return companyDao.findAll();
	}

}
