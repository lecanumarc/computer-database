package com.excilys.formation.cdb.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.excilys.formation.cdb.dto.CompanyDto;
import com.excilys.formation.cdb.dto.ComputerDto;
import com.excilys.formation.cdb.pojos.Company;
import com.excilys.formation.cdb.pojos.Computer;

import com.excilys.formation.cdb.pojos.Computer.ComputerBuilder;

public class ComputerMapper {

	public static Computer map(ResultSet result) throws SQLException {
		Company company = null;
		if(result.getLong("company_id") != 0) {
		} else if (result.getString("company_name") != null &&
				result.getString("company_name").isEmpty()) {
			company = new Company(result.getLong("company_id"), "");
		} else {
			company= new Company(result.getLong("company_id"), result.getString("company_name"));
		}

		Computer computer = new ComputerBuilder(result.getString("computer_name"))
				.setId(result.getLong("computer_id"))
				.setIntroduced(result.getDate("introduced").toLocalDate())
				.setDiscontinued(result.getDate("discontinued").toLocalDate())
				.setCompany(company)
				.build();	
		return computer;
	}

	public static ComputerDto toDto(Computer computer ) throws SQLException {

		CompanyDto companyDto = new CompanyDto(computer.getCompany().getId(),
				computer.getCompany().getName()
				);	
		
		ComputerDto computerDto = new ComputerDto(computer.getId(),
				computer.getName(),
				computer.getintroduced().toString(),
				computer.getdiscontinued().toString(),
				companyDto);
		
		return computerDto;
	}

}
