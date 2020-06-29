package com.excilys.formation.cdb.dto;

public class CompanyDto {

	private Long id;
	private String name;

	public CompanyDto() {
	}
	
	public CompanyDto(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	
	public Long getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	@Override
	public String toString() {
		return "CompanyDto [id=" + id + ", name=" + name + "]";
	}
}
