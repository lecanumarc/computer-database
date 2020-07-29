package com.excilys.formation.cdb.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.excilys.formation.cdb.validator.ComputerValidator;

@Entity
@Table(name = "computer")
public class Computer {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "name")
	private String name; 
	@Column(name = "introduced")
	private LocalDate introduced; 
	@Column(name = "discontinued")
	private LocalDate discontinued;
	@ManyToOne
	@JoinColumn(name = "company_id")
	private Company company;


	private Computer() {
		
	}
	
	private Computer(ComputerBuilder computerBuilder) {
		super();
		this.id = computerBuilder.id;
		this.name = computerBuilder.name;
		if(computerBuilder.introduced != null) {
			this.introduced = computerBuilder.introduced;
		}
		if(computerBuilder.discontinued != null) {
			this.discontinued = computerBuilder.discontinued;
		}
		if(computerBuilder.company != null) {
			this.company = computerBuilder.company;
		}
	}

	public Long getId() {
		return id;
	}


	public String getName() {
		return name;
	}

	public LocalDate getIntroduced() {
		return introduced;
	}


	public LocalDate getDiscontinued() {
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
			this.name = name;
		}

		public ComputerBuilder setId(Long id) {
			this.id = id;
			return this;
		}

		public ComputerBuilder setIntroduced(LocalDate introduced) {
			this.introduced = introduced;
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
			computer.validate();
			return computer;
		}

	}

}
