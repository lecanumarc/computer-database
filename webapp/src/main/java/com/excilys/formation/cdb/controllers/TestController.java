package com.excilys.formation.cdb.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.excilys.formation.cdb.daos.CompanyRepository;
import com.excilys.formation.cdb.dto.ComputerDto;
import com.excilys.formation.cdb.mapper.ComputerMapper;
import com.excilys.formation.cdb.models.Computer;
import com.excilys.formation.cdb.services.CompanyDaoProvider;
import com.excilys.formation.cdb.services.ComputerDaoProvider;

@Controller
@RequestMapping({"/company"})
public class TestController {

	//	private static Logger logger = LoggerFactory.getLogger(EditComputerController.class);

	CompanyDaoProvider companyDaoProvider; 
	ComputerDaoProvider computerDaoProvider; 

	@Autowired
	public TestController(ComputerDaoProvider computerDaoProvider, CompanyDaoProvider companyDaoProvider) {
		this.computerDaoProvider = computerDaoProvider;
		this.companyDaoProvider = companyDaoProvider;
	}

	@GetMapping
	public String TestGet(@RequestParam(value="id") Long id,
			ModelMap dataMap) {
		List<Computer> computerList = computerDaoProvider.listByCompanyId(id);
		companyDaoProvider.findById(id);
		System.out.println(companyDaoProvider.findById(id).get());
		dataMap.addAttribute("computerList", computerList);
		return "company";

	}

}
