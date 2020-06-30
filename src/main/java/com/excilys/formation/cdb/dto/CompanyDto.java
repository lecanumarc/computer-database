package com.excilys.formation.cdb.dto;

public class CompanyDto {

	private Long id;
	private String name;

	public CompanyDto() {
	}
	
	public CompanyDto(Long id) {
		super();
		this.id = id;
	}
	
	public Long getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "CompanyDto [id=" + id + ", name=" + name + "]";
	}
}
