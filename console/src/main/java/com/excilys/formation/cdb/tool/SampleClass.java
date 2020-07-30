package com.excilys.formation.cdb.tool;

import org.springframework.beans.factory.annotation.Autowired;

import com.excilys.formation.cdb.cli.UserCLI;
import com.excilys.formation.cdb.controller.CompanyController;
import com.excilys.formation.cdb.controller.ComputerController;

public class SampleClass {

	public UserCLI cli;
	
	static CompanyController companyController;
	static ComputerController computerController;

	@Autowired
	public SampleClass(CompanyController companyController, ComputerController computerController) {
		this.companyController = companyController;
		this.computerController = computerController;
	}
	
	public static void main(String[] args) {
		System.out.println("begin");
		System.out.println(SampleClass.companyController.getCompanyList());
	}
}
