package com.excilys.formation.cdb.controllers;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.excilys.formation.cdb.dto.CompanyDto;
import com.excilys.formation.cdb.dto.ComputerDto;
import com.excilys.formation.cdb.mapper.CompanyMapper;
import com.excilys.formation.cdb.mapper.ComputerMapper;
import com.excilys.formation.cdb.pojos.Company;
import com.excilys.formation.cdb.pojos.Computer;
import com.excilys.formation.cdb.services.CompanyDaoProvider;
import com.excilys.formation.cdb.services.ComputerDaoProvider;

@Controller
@RequestMapping("/addComputer")
public class AddComputerController {

	@Autowired
	private CompanyDaoProvider companyDaoProvider;
	@Autowired
	private ComputerDaoProvider computerDaoProvider;

	private static Logger logger = LoggerFactory.getLogger(AddComputerController.class);

	@GetMapping
	public String getAddComputer(@ModelAttribute ComputerDto computerDto, Model model) {

		List<Company> companies = companyDaoProvider.listCompanies();
		List<CompanyDto> companyList = new ArrayList<CompanyDto>();
		companyList = companies.stream()
				.map(company->CompanyMapper.CompanyToDto(company))
				.collect(Collectors.toList());		
		model.addAttribute("companyList", companyList);
		return "addComputer";
	}

	@PostMapping
	public String postAddComputer(@ModelAttribute ComputerDto computerDto,
			Model model) {
		try {
			Computer computer = ComputerMapper.DtoToComputer(computerDto);
			computerDaoProvider.add(computer);
		} catch (SQLException e) {
			logger.error("SQLException ",e);
		}
		return getAddComputer(computerDto,model);
	}

	@ModelAttribute
	public ComputerDto getComputerDto(@RequestParam(required=false,name="name") String computerName,
			@RequestParam(required=false, name="introduced") String introduced,
			@RequestParam(required=false, name="discontinued") String discontinued,
			@RequestParam(required=false, name="companyId") String companyId,
			Model model) {
		CompanyDto companyDto = new CompanyDto(Long.valueOf(companyId));
		ComputerDto computerDto = new ComputerDto(new Long(0) , computerName);
		computerDto.setCompany(companyDto);
		computerDto.setIntroduced(introduced);
		computerDto.setDiscontinued(discontinued);
		return computerDto;
	}

}
