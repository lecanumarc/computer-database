package com.excilys.formation.cdb.controllers;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
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
@RequestMapping("editComputer")
public class EditComputerController {

	private static Logger logger = LoggerFactory.getLogger(EditComputerController.class);
	
	CompanyDaoProvider companyDaoProvider; 
	ComputerDaoProvider computerDaoProvider; 

	@Autowired
	public EditComputerController(ComputerDaoProvider computerDaoProvider, CompanyDaoProvider companyDaoProvider) {
		this.computerDaoProvider = computerDaoProvider;
		this.companyDaoProvider = companyDaoProvider;
	}

	@GetMapping
	public String editComputerGet(@RequestParam(value="id") Long id,
			ModelMap dataMap) {
		Computer computerToUpdate = computerDaoProvider.findById(id);
		List<CompanyDto> companyDtoList = getCompanyDtoList();
		dataMap.addAttribute("companyList", companyDtoList);
		dataMap.addAttribute("id", id);
		dataMap.addAttribute("computerToUpdate", computerToUpdate);
		return "editComputer";

	}

	@PostMapping
	public String postAddComputer(@RequestParam(required=false, name="companyId") Long companyId,
			@RequestParam(required=false, name="id") Long id,
			@RequestParam(required=false, name="computerName") String computerName,
			@RequestParam(required=false, name="introduced") String introduced,
			@RequestParam(required=false, name="discontinued") String discontinued) {

		CompanyDto companyDto = new CompanyDto(companyId);
		ComputerDto computerDto = new ComputerDto(computerName, introduced, discontinued, companyDto);
		computerDto.setId(id);
		try {
			Computer computer = ComputerMapper.DtoToComputer(computerDto);
			System.out.println(computer);
			computerDaoProvider.edit(computer);
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
		return "redirect:/dashboard";
	}

	private List<CompanyDto> getCompanyDtoList(){
		List<Company> companyList = companyDaoProvider.listCompanies();
		List<CompanyDto> companyDtoList = companyList.stream()
				.map((Company company)-> CompanyMapper.CompanyToDto(company))
				.collect(Collectors.toList());
		return companyDtoList;
	}


}
