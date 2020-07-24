package com.excilys.formation.cdb.controllers;


import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.excilys.formation.cdb.daos.CompanyRepository;
import com.excilys.formation.cdb.daos.ComputerRepository;
import com.excilys.formation.cdb.dto.CompanyDto;
import com.excilys.formation.cdb.dto.ComputerDto;
import com.excilys.formation.cdb.mapper.CompanyMapper;
import com.excilys.formation.cdb.mapper.ComputerMapper;
import com.excilys.formation.cdb.pojos.Company;
import com.excilys.formation.cdb.pojos.Computer;
import com.excilys.formation.cdb.services.CompanyDaoProvider;
import com.excilys.formation.cdb.services.ComputerDaoProvider;

@Controller
@RequestMapping({"/company"})
public class TestController {

	private static Logger logger = LoggerFactory.getLogger(EditComputerController.class);

	CompanyDaoProvider companyDaoProvider; 
	ComputerDaoProvider computerDaoProvider; 
	CompanyRepository instanceDAO;

	@Autowired
	public TestController(CompanyRepository instanceDAO, ComputerDaoProvider computerDaoProvider, CompanyDaoProvider companyDaoProvider) {
		this.computerDaoProvider = computerDaoProvider;
		this.companyDaoProvider = companyDaoProvider;
		this.instanceDAO = instanceDAO;
	}

	@GetMapping
	public String TestGet(@RequestParam(value="id") Long id,
			ModelMap dataMap) {
		Page<Computer> computerList = computerDaoProvider.listByPage(0, 10);
		System.out.println(computerList.getContent());
		List<ComputerDto> dtoList = computerList.stream()
		.map((Computer computer) ->  {
			try {
				return ComputerMapper.ComputerToDto(computer);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return null;
		})
		.collect(Collectors.toList());
		
		dataMap.addAttribute("computerList", dtoList);
		return "company";

	}

}
