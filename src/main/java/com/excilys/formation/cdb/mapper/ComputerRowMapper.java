package com.excilys.formation.cdb.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.excilys.formation.cdb.pojos.Company;
import com.excilys.formation.cdb.pojos.Computer;
import com.excilys.formation.cdb.pojos.Computer.ComputerBuilder;

public class ComputerRowMapper implements RowMapper<Computer> {
	@Override
	public Computer mapRow(ResultSet rs, int rowNum) throws SQLException {
		Company company = new CompanyRowMapper().mapRow(rs, rowNum);

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