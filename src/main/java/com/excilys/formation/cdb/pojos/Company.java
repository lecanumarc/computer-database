package com.excilys.formation.cdb.pojos;

import java.time.LocalDate;

import com.excilys.formation.cdb.pojos.Computer.ComputerBuilder;

public class Company {

	Long id;
	String name;

	public Company() {
		super();
	}

	public Company(CompanyBuilder companyBuilder) {
		super();
		this.id = companyBuilder.id;
		this.name = companyBuilder.name;
	}

	public Long getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		if(name != null) {
			this.name = name;
		}
	}

	public static class CompanyBuilder{
		private Long id;
		private String name;

		public CompanyBuilder() {
		}

		public CompanyBuilder setId(Long id) {
			this.id = id;
			return this;
		}

		public CompanyBuilder setName(String name) {
			this.name = name;
			return this;
		}

		public Company build() {
			Company computer = new Company(this);
			return computer;
		}

	}
	@Override
	public String toString() {
		return "Company [id=" + id + ", name=" + name + "]";
	}


}
