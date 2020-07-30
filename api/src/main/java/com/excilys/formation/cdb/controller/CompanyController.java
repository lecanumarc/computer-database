package com.excilys.formation.cdb.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

	@GetMapping(value = "page/{index}/{rows}")
	public ResponseEntity<List<Company>> getCompanyPage(@PathVariable("index") int index, @PathVariable("rows") int rows) {
		System.out.println("page");
		Page<Company> companies = companyDaoProvider.listByPage(index, rows);
		return new ResponseEntity<List<Company>>(companies.getContent(), HttpStatus.OK);
	}

	@GetMapping(value = "page")
	public ResponseEntity<List<Company>> getCompanyPage(
			@RequestParam(name="index", required = true) int index,
			@RequestParam(name="rows", required = true) int rows,
			@RequestParam(name="filter", required = false, defaultValue = "") String filter,
			@RequestParam(name="column", required = false, defaultValue = "id") String column,
			@RequestParam(name="ascOrder" , required = false, defaultValue = "true") boolean ascOrder
			) {
		Page<Company> companies = companyDaoProvider.listOrderedAndFiltered(index, rows, filter, column, ascOrder);
		return new ResponseEntity<List<Company>>(companies.getContent(), HttpStatus.OK);
	}

	@GetMapping(value = "/list")
	public ResponseEntity<List<Company>> getCompanyList() {
		List<Company> companyList = companyDaoProvider.listCompanies();
		return new ResponseEntity<List<Company>>(companyList, HttpStatus.OK);
	}

	@PutMapping(value = "/delete/{id}")
	public ResponseEntity<Company> deleteCompany(@PathVariable("id") long id) {
		companyDaoProvider.delete(id);
		return new ResponseEntity<Company>(HttpStatus.OK);
	}

	@PutMapping(value = "/update/{id}")
	public ResponseEntity<Company> updateCompany(@RequestBody Company company, @PathVariable long id) {

		Optional<Company> companyFound = companyDaoProvider.findById(id);

		if (!companyFound.isPresent()) {
			return new ResponseEntity<Company>(HttpStatus.NOT_FOUND);
		}

		companyFound.get().setId(id);
		companyDaoProvider.edit(companyFound.get());
		return new ResponseEntity<Company>(HttpStatus.OK);
	}
	
	@PostMapping(value = "/add", consumes  =  {"text/plain;charset=UTF-8", MediaType.APPLICATION_JSON_VALUE},  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Company> updateCompany(@RequestBody Company company) {
		System.out.println(company);
		companyDaoProvider.add(company);
		return new ResponseEntity<Company>(HttpStatus.OK);
	}

}
