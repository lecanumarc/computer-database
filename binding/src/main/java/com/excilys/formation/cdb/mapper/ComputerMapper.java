package com.excilys.formation.cdb.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;

import com.excilys.formation.cdb.dto.CompanyDto;
import com.excilys.formation.cdb.dto.ComputerDto;
import com.excilys.formation.cdb.model.Company;
import com.excilys.formation.cdb.model.Computer;
import com.excilys.formation.cdb.model.Computer.ComputerBuilder;

public class ComputerMapper {

	public static Computer map(ResultSet result) throws SQLException {
		Company company = null;
		if(result.getLong("company_id") != 0) {
			if (result.getString("company_name") == null) {
				company = new Company.CompanyBuilder().setId(result.getLong("company_id")).build();
			} else {
				company =  new Company.CompanyBuilder().setId(result.getLong("company_id")).setName(result.getString("company_name")).build();
			}
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

	public static ComputerDto ComputerToDto(Computer computer) throws SQLException {
		CompanyDto companyDto = null;
		ComputerDto computerDto = null;
		if(computer != null) {
			companyDto = CompanyMapper.CompanyToDto(computer.getCompany());

			computerDto = new ComputerDto(computer.getId(), computer.getName());

			if(computer.getIntroduced() != null) {
				computerDto.setIntroduced(computer.getIntroduced().format(DateTimeFormatter.ISO_DATE));
			}

			if(computer.getDiscontinued() != null) {
				computerDto.setDiscontinued(computer.getDiscontinued().format(DateTimeFormatter.ISO_DATE));
			}

			computerDto.setCompany(companyDto);
		}

		return computerDto;
	}

	public static Computer DtoToComputer(ComputerDto computerDto) throws SQLException {
		Computer computer = null;
		if(computerDto != null) {
			ComputerBuilder computerBuilder = new ComputerBuilder(computerDto.getName());
			if(computerDto.getId() != null) {
				computerBuilder.setId(computerDto.getId());
			}
			if(computerDto.getIntroduced() != null && !computerDto.getIntroduced().isEmpty()) {
				computerBuilder.setIntroduced(LocalDate.parse(computerDto.getIntroduced()));
			}
			if(computerDto.getDiscontinued() != null && !computerDto.getDiscontinued().isEmpty()) {
				computerBuilder.setDiscontinued(LocalDate.parse(computerDto.getDiscontinued()));
			}
			if(computerDto.getCompany() != null) {
				computerBuilder.setCompany(CompanyMapper.DtoToCompany(computerDto.getCompany()));
			}
			computer = computerBuilder.build();
		}

		return computer;
	}
	
	static public List<ComputerDto> getComputerDtoList(Page<Computer> computerList){
		List<ComputerDto> dtoList = computerList.stream()
				.map((Computer computer)-> {
					try {
						return ComputerMapper.ComputerToDto(computer);
					} catch (SQLException e) {
						e.printStackTrace();
					}
					return null;
				})
				.collect(Collectors.toList());
		return dtoList;
	}
	
	static public List<ComputerDto> getComputerDtoList(List<Computer> computerList){
		List<ComputerDto> dtoList = computerList.stream()
				.map((Computer computer)-> {
					try {
						return ComputerMapper.ComputerToDto(computer);
					} catch (SQLException e) {
						e.printStackTrace();
					}
					return null;
				})
				.collect(Collectors.toList());
		return dtoList;
	}
	
	
}
