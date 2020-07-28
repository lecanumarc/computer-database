package com.excilys.formation.cdb.services;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

import com.excilys.formation.cdb.pojos.Company;
import com.excilys.formation.cdb.pojos.Computer;
import com.excilys.formation.cdb.pojos.Computer.ComputerBuilder;

public class UserScanner {
	private static Scanner scanner;
	private static UserScanner instance;
	private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	private UserScanner() {
		scanner = new Scanner(System.in);
	}

	public static UserScanner getInstance(){
		if(instance == null){
			instance = new UserScanner();
		}
		return instance;   
	}   

	public int nextInt() throws Exception {
		int number;
		if(scanner.hasNextInt()) {
			number = scanner.nextInt();
			return number;
		} else {
			throw new Exception("Enter a valid integer.");
		}
	}

	public String nextLine() throws Exception {
		String line;
		if(scanner.hasNextLine()) {
			line = scanner.nextLine();
			return line;
		} else {
			throw new Exception("Enter a valid line.");
		}
	}

	public char nextChar() throws Exception {
		char character;
		if(scanner.hasNextLine()) {
			character = scanner.next().charAt(0);
			return character;
		} else {
			throw new Exception("Enter a valid line.");
		}
	}

	public Computer getComputer() throws Exception {

		System.out.println("\nEnter computer id : ...");
		Long id = new Long(this.nextInt());
		this.nextLine(); // consume "\n" from previous input

		System.out.println("\nEnter computer name : ...");
		String name = this.nextLine();

		System.out.println("\nEnter introduction date : (yyyy-MM-dd) ...");
		String str = this.nextLine();
		LocalDate introDate = null;
		try{
			introDate = LocalDate.parse(str, formatter);
		}
		catch(DateTimeParseException e){
			introDate = null;
		}
		System.out.println("\nEnter discontinuation date : (yyyy-MM-dd)...");
		str = this.nextLine();
		LocalDate discDate = null;
		try{
			discDate = LocalDate.parse(str, formatter);
		}
		catch(DateTimeParseException e){
			discDate = null;
		}

		
		System.out.println("\nEnter company id : (integer)...");
		Long companyId = new Long(this.nextInt());
		this.nextLine(); // consume "\n" from previous input

		Company company = new Company.CompanyBuilder().setId(companyId).build();

		Computer computer = new ComputerBuilder(name)
							.setId(id)
							.setIntroduced(introDate)
							.setDiscontinued(discDate)
							.setCompany(company)
							.build();
		return computer;

	}

}
