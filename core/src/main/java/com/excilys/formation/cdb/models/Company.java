package com.excilys.formation.cdb.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="company")
public class Company {
	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	Long id;
	@Column(name="name")
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
