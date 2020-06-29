package com.excilys.formation.cdb.pojos;

import java.time.LocalDate;

import com.excilys.formation.cdb.validator.ComputerValidator;

public class Computer {
	private Long id;
	private String name; // mandatory
	private LocalDate introduced; 
	private LocalDate discontinued; 
	private Company company;


	private Computer(ComputerBuilder computerBuilder) {
		super();
		this.id = computerBuilder.id;
		this.name = computerBuilder.name;
		this.introduced = computerBuilder.introduced;
		this.discontinued = computerBuilder.discontinued;
		this.company = computerBuilder.company;
	}

	public Long getId() {
		return id;
	}


	public String getName() {
		return name;
	}

	public LocalDate getintroduced() {
		return introduced;
	}


	public LocalDate getdiscontinued() {
		return discontinued;
	}


	public Company getCompany() {
		return company;
	}

	@Override
	public String toString() {
		return "Computer [id=" + id + ", name=" + name + ", introduced=" + introduced + ", discontinued=" + discontinued
				+ ", company=" + company + "]";
	}

	public void validate() {
		ComputerValidator.validate(this);
	}


	public static class ComputerBuilder{
		private Long id;
		private String name; // mandatory
		private LocalDate introduced; 
		private LocalDate discontinued; 
		private Company company;
		
		public  ComputerBuilder(String name) {
			this.name=name;
		}

		public ComputerBuilder setId(Long id) {
			this.id = id;
			return this;
		}

		public ComputerBuilder setIntroduced(LocalDate introduced) {
			this.introduced=introduced;
			return this;
		}

		public ComputerBuilder setDiscontinued(LocalDate discontinued) {
			this.discontinued = discontinued;
			return this;
		}

		public ComputerBuilder setCompany(Company company) {
			this.company = company;
			return this;
		}

		public Computer build() {
			Computer computer = new Computer(this);
			return computer;
		}

	}

}
