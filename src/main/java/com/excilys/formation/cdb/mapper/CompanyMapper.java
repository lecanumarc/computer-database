package com.excilys.formation.cdb.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.excilys.formation.cdb.dto.CompanyDto;
import com.excilys.formation.cdb.dto.ComputerDto;
import com.excilys.formation.cdb.pojos.Company;
import com.excilys.formation.cdb.pojos.Computer;

public class CompanyMapper {

	public static Company map(ResultSet result) {
		Company company = new Company();
		try {
			company.setName(result.getString("name"));
			company.setId(result.getLong("id"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return company;
	}

	public static CompanyDto CompanyToDto(Company company) throws SQLException {
		CompanyDto companyDto = new CompanyDto();
		
		if(company != null && company.getId() != 0) {
			companyDto.setId(company.getId());
			if(company.getName() != null) {
				companyDto.setName(company.getName());
			}
		}
		
		return companyDto;
	}

	public static Company DtoToCompany(CompanyDto companyDto) throws SQLException {
		Company company = new Company();
		if(companyDto != null) {
			company.setName(companyDto.getName());
			company.setId(companyDto.getId());
		}
		
		return company;
	}


}
