package com.excilys.formation.cdb.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import com.excilys.formation.cdb.pojos.Computer;

public class ComputerMapper {

	public static Computer map(ResultSet result) {
		Computer computer = new Computer();
		try {
			computer.setId(result.getInt("id"));
			computer.setName(result.getString("name"));
			if(result.getDate("introduced") != null) {
				computer.setIntroDate(result.getDate("introduced").toLocalDate());
			}
			if(result.getDate("discontinued") != null) {
				computer.setDiscDate(result.getDate("discontinued").toLocalDate());
			}
			computer.setCompanyId(result.getInt("company_id"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return computer;
	}
}
