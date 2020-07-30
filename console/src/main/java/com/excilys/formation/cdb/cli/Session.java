package com.excilys.formation.cdb.cli;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.excilys.formation.cdb.controller.CompanyController;
import com.excilys.formation.cdb.controller.ComputerController;
import com.excilys.formation.cdb.dto.CompanyDto;
import com.excilys.formation.cdb.dto.ComputerDto;
import com.excilys.formation.cdb.mapper.CompanyMapper;
import com.excilys.formation.cdb.mapper.ComputerMapper;
import com.excilys.formation.cdb.model.Company;
import com.excilys.formation.cdb.model.Computer;
import com.excilys.formation.cdb.tool.Menu;
import com.excilys.formation.cdb.tool.Page;

public class Session {

	public static enum Request{
		GET_COMPUTER,
		ADD_COMPUTER,
		UPDATE_COMPUTER,
		GET_COMPUTER_PAGE,
		GET_COMPUTER_LIST,
		GET_COMPANY,
		GET_COMPANY_PAGE,
		GET_COMPANY_LIST,
		ADD_COMPANY,
		_COMPANY_LIST,
		QUIT,
		GET_MAIN_MENU,
		NAVIGUATION,
	}
	
	
	private Page page;
	private ComputerController computerController;
	private CompanyController companyController;
	
	@Autowired
	public Session(ComputerController computerController, CompanyController companyController){
		this.computerController = computerController;
		this.companyController = companyController;
		
	}
	
	public Page getMainMenuPage() {
		Page page = new Page("Main Menu", 0, Menu.getMessage(Menu.MessageType.MAIN_MENU));
		return page;
	}

	public Page getGoodbyePage() {
		Page page = new Page("Logout", 0, Menu.getMessage(Menu.MessageType.GOOD_BYE));
		return page;
	}
	
//	public Page getComputerPage(int index, int rows) {
//		List<Computer> computerList = computerController.getComputerPage(index, rows).getBody();
//		List<ComputerDto> computerDto = ComputerMapper.getComputerDtoList(computerList);
//		Page page = new Page("Computer list (" +computerList.size() +" computers)",
//				index,
//				computerDto + ""+ Menu.getMessage(Menu.MessageType.NAVIGUATION));
//		return page;
//	}

	public Page getCompanyList(int index, int rows) {
		List<Company> companyList = companyController.getCompanyPage(index, rows).getBody();
		List<CompanyDto> companyDto = CompanyMapper.getCompanyDtoList(companyList);
		Page page = new Page("Computer list" +companyList.size() +" companies)", 
				index, 
				companyDto + Menu.getMessage(Menu.MessageType.NAVIGUATION));
		return page;
	}
	
	public Page getPage() {
		return page;
	}
	
	public void setPage(Page page) {
		this.page = page;
	}
}
