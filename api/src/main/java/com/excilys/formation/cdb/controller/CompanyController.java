package com.excilys.formation.cdb.controller;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.excilys.formation.cdb.model.Company;
import com.excilys.formation.cdb.service.CompanyDaoProvider;

@RestController
@RequestMapping({"/company"})
public class CompanyController {
	CompanyDaoProvider companyDaoProvider;

	@Autowired
	public CompanyController(CompanyDaoProvider companyDaoProvider) {
		this.companyDaoProvider = companyDaoProvider;
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Company> getCompanyById(@PathVariable("id") long id) {
		Optional<Company> company = companyDaoProvider.findById(id);
		if(company.isPresent()) {
			return new ResponseEntity<Company>(company.get(), HttpStatus.OK);
		}
		return new ResponseEntity<Company>(HttpStatus.NOT_FOUND);
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<Company> deleteCompany(@PathVariable("id") long id) {
		companyDaoProvider.delete(id);
		return new ResponseEntity<Company>(HttpStatus.OK);
	}
}
