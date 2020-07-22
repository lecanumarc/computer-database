package com.excilys.formation.cdb.controllers;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.excilys.formation.cdb.daos.CompanyRepository;
import com.excilys.formation.cdb.daos.ComputerRepository;
import com.excilys.formation.cdb.pojos.Company;
import com.excilys.formation.cdb.pojos.Computer;

@Controller
@RequestMapping({"/company"})
public class TestController {

	private CompanyRepository companyRepository;
	private ComputerRepository computerRepository;

	@Autowired
	public TestController(CompanyRepository companyRepository, ComputerRepository computerRepository) {
		this.companyRepository = companyRepository;
		this.computerRepository = computerRepository;
	}

	@GetMapping
	public String findById(@RequestParam(required=true, name="id") Long id, Model model) {
		List<Computer> computer = computerRepository.findAll();
		Optional<Company> company = companyRepository.findById(id);
		model.addAttribute("computer", computer);
		model.addAttribute("company", company);
		return "company";
	}
}
