package com.excilys.formation.cdb.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.excilys.formation.cdb.pojos.Company;

public class CompanyMapper {

	public static Company map(ResultSet result) {
		Company company = new Company();
		try {
			company.setName(result.getString("name"));
			company.setId(result.getInt("id"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return company;
	}



}
