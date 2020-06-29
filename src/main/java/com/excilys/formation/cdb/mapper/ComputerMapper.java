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
		if(result.getLong("company_id") == 0) {
		} else if (result.getString("company_name") == null) {
			company = new Company(result.getLong("company_id"), "");
		} else {
			company= new Company(result.getLong("company_id"), result.getString("company_name"));
		}

		ComputerBuilder computerBuilder = new ComputerBuilder(result.getString("computer_name")).setId(result.getLong("computer_id"));
		if(company != null) {
			computerBuilder.setCompany(company);
		}
		if(result.getDate("introduced") != null) {
			computerBuilder.setIntroduced(result.getDate("introduced").toLocalDate());
		}
		if(result.getDate("discontinued")!= null) {
			computerBuilder.setDiscontinued(result.getDate("discontinued").toLocalDate());
		}
		return computerBuilder.build();
		
	}

	public static ComputerDto toDto(Computer computer) throws SQLException {

		CompanyDto companyDto = new CompanyDto();
		
		if(computer.getCompany().getId() != 0) {
			companyDto.setId(computer.getCompany().getId());
			if(computer.getCompany().getName() != null) {
				companyDto.setName(computer.getCompany().getName());
			}
		}
				
		ComputerDto computerDto = new ComputerDto(computer.getId(), computer.getName());
		
		if(computer.getintroduced() != null) {
			computerDto.setIntroduced(computer.getintroduced().toString());
		}
		
		if(computer.getdiscontinued() != null) {
			computerDto.setIntroduced(computer.getintroduced().toString());
		}
		
		computerDto.setCompany(companyDto);
		return computerDto;
	}

}
