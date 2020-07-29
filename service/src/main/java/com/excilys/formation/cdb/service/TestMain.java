package com.excilys.formation.cdb.service;

import org.springframework.beans.factory.annotation.Autowired;

public class TestMain {

	static CompanyDaoProvider companyDaoProvider; 
	static ComputerDaoProvider computerDaoProvider; 

	@Autowired
	public TestMain(ComputerDaoProvider computerDaoProvider, CompanyDaoProvider companyDaoProvider) {
		this.computerDaoProvider = computerDaoProvider;
		this.companyDaoProvider = companyDaoProvider;
	}
	public static void main(String[] args) throws Exception {
		System.out.println("begin");
		System.out.println("end");
	}	
}
