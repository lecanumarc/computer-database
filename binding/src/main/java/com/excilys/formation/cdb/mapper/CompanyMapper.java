package com.excilys.formation.cdb.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.formation.cdb.dto.CompanyDto;
import com.excilys.formation.cdb.model.Company;
import com.excilys.formation.cdb.model.Company.CompanyBuilder;

public class CompanyMapper {

	private static Logger logger = LoggerFactory.getLogger(CompanyMapper.class);

	public static Company map(ResultSet result) {
		Company company = null;
		try {
			CompanyBuilder companyBuilder = new Company.CompanyBuilder();
			if(result.getString("name") != null && !result.getString("name").isEmpty()) {
				companyBuilder.setName(result.getString("name"));
			}
			if(result.getLong("id") != 0) {
				companyBuilder.setId(result.getLong("id"));
			}

			company = companyBuilder.build();

		} catch (SQLException e) {
			logger.error("Error during mapping",e);
		}
		return company;
	}

	public static CompanyDto CompanyToDto(Company company) {
		CompanyDto companyDto = new CompanyDto();

		if(company != null && company.getId() != 0) {
			companyDto.setId(company.getId());
			if(company.getName() != null) {
				companyDto.setName(company.getName());
			}
		}

		return companyDto;
	}

	public static Company DtoToCompany(CompanyDto companyDto) {
		Company company = new Company();
		if(companyDto != null) {
			CompanyBuilder companyBuilder = new Company.CompanyBuilder();
			if(companyDto.getName() != null && !companyDto.getName().isEmpty()) {
				companyBuilder.setName(companyDto.getName());
			}
			if(companyDto.getId() != 0) {
				companyBuilder.setId(companyDto.getId());
			}
			company = companyBuilder.build();
		}

		return company;
	}

	public static List<CompanyDto> getCompanyDtoList(List<Company> companyList){
		List<CompanyDto> dtoList = companyList.stream()
				.map((Company company)-> {
					return CompanyMapper.CompanyToDto(company);
				})
				.collect(Collectors.toList());
		return dtoList;
	}

}
