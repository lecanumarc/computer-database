
package com.excilys.formation.cdb.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.excilys.formation.cdb.model.Company;
import com.excilys.formation.cdb.model.Company.CompanyBuilder;

public class CompanyRowMapper implements RowMapper<Company> {
	@Override
	public Company mapRow(ResultSet rs, int rowNum) throws SQLException {
		CompanyBuilder companyBuilder = new CompanyBuilder();
		if(rs.getLong("company_id") != 0){
			companyBuilder.setId(rs.getLong("company_id"));
		}
		if(rs.getString("company_name") != null) {
			companyBuilder.setName(rs.getString("company_name"));
		}

		Company company = companyBuilder.build();

		return company;
	}
}
