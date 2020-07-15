package com.excilys.formation.cdb.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.excilys.formation.cdb.pojos.Company;
import com.excilys.formation.cdb.pojos.Computer;
import com.excilys.formation.cdb.pojos.Company.CompanyBuilder;
import com.excilys.formation.cdb.pojos.Computer.ComputerBuilder;

public class ComputerRowMapper implements RowMapper<Computer> {
	@Override
	public Computer mapRow(ResultSet rs, int rowNum) throws SQLException {
		CompanyBuilder companyBuilder = new CompanyBuilder();
		if(rs.getLong("company_id") != 0){
			companyBuilder.setId(rs.getLong("company_id"));
		}
		if(rs.getString("company_name") != null) {
			companyBuilder.setName(rs.getString("company_name"));
		}

		Company company = companyBuilder.build();

		ComputerBuilder computerBuilder = new ComputerBuilder(rs.getString("computer_name"));
		if(company != null) {
			computerBuilder.setCompany(company);
		}
		if(rs.getDate("discontinued") != null) {
			computerBuilder.setDiscontinued(rs.getDate("discontinued").toLocalDate());
		}
		if(rs.getDate("introduced") != null) {
			computerBuilder.setIntroduced(rs.getDate("introduced").toLocalDate());
		}
		if(rs.getLong("computer_id") != 0){
			computerBuilder.setId(rs.getLong("computer_id"));
		}
		Computer computer = computerBuilder.build();
		return computer;
	}
}