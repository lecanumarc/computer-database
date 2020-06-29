package com.excilys.formation.cdb.dto;

public class ComputerDto {

	public ComputerDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	private Long id;
	public String name;
	private String introduced;
	private String discontinued;
	private CompanyDto company;

	public ComputerDto(String name, String introduced, String discontinued, CompanyDto company) {
		super();
		this.name = name;
		this.introduced = introduced;
		this.discontinued = discontinued;
		this.company = company;
	}
	public ComputerDto(Long id, String name, String introduced, String discontinued, CompanyDto CompanyDto) {
		super();
		this.id=id;
		this.name = name;
		this.introduced = introduced;
		this.discontinued = discontinued;
		this.company = CompanyDto;
	}
	public Long getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public String getIntroduced() {
		return introduced;
	}
	public String getDiscontinued() {
		return discontinued;
	}
	public CompanyDto getCompany() {
		return company;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setIntroduced(String introduced) {
		this.introduced = introduced;
	}
	public void setDiscontinued(String discontinued) {
		this.discontinued = discontinued;
	}
	public void setCompany(CompanyDto company) {
		this.company = company;
	}
	@Override
	public String toString() {
		return "ComputerDto [id=" + id + ", name=" + name + ", introduced=" + introduced + ", discontinued="
				+ discontinued + ", company=" + company + "]";
	}

}
