package com.excilys.formation.cdb.pojos;

import java.time.LocalDate;

import com.excilys.formation.cdb.validator.ComputerValidator;

public class Computer {
	private int id;
	private String name; // mandatory
	private LocalDate introDate; 
	private LocalDate discDate; 
	private int companyId;

	public Computer(int id, String name, LocalDate introDate, LocalDate discDate, int companyId) throws Exception {
		super();
		if(name.isEmpty()) {
			throw new IllegalArgumentException("Name cannot be empty");
		}
		if(introDate != null && discDate != null && introDate.compareTo(discDate) > 0) {
			throw new IllegalArgumentException("Introduction date must not be greater than discontinuation date.");
		}
		this.id = id;
		this.name = name;
		if(introDate != null) {
			this.introDate = introDate;
		}
		if(discDate != null) {
			this.discDate = discDate;
		}
		this.companyId = companyId;
	}

	public Computer() {
		super();
	}

	public String getName() {
		return name;
	}
	public void setName(String name) throws Exception {
		if(name.isEmpty()) {
			throw new IllegalArgumentException("Name cannot be empty");
		}
		this.name = name;
	}
	public LocalDate getIntroDate() {
		return introDate;
	}
	public void setIntroDate(LocalDate introDate) throws Exception {
		if(introDate != null && this.discDate != null && introDate.compareTo(this.discDate) > 0) {
			throw new IllegalArgumentException("Introduction date must not be greater than discontinuation date.");
		}
		this.introDate = introDate;
	}
	public LocalDate getDiscDate() {
		return discDate;
	}
	public void setDiscDate(LocalDate discDate) throws Exception {	
		if(this.introDate != null && discDate != null && this.introDate.compareTo(discDate) > 0) {
			throw new IllegalArgumentException("Introduction date must be greater than discontinuation date.");

		}
		this.discDate = discDate;
	}
	public int getCompanyId() {
		return companyId;
	}
	public void setCompanyId(int companyId) throws Exception {
		this.companyId = companyId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Computer [id=" + id + ", name=" + name + ", introDate=" + introDate + ", discDate=" + discDate
				+ ", companyId=" + companyId + "]";
	}
	
	public void validate() {
		ComputerValidator.validate(this);
	}


}
