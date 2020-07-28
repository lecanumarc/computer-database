package com.excilys.formation.cdb.daos;

import java.sql.Date;
import java.sql.Types;
import java.util.List;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.excilys.formation.cdb.exceptions.ComputerValidatorException;
import com.excilys.formation.cdb.mapper.ComputerRowMapper;
import com.excilys.formation.cdb.models.Computer;

@Repository
public class ComputerDAO implements DAO<Computer>{

	//	SQL queries
	private static final String COUNT_QRY = "SELECT COUNT(computer.id) FROM computer";
	private static final String COUNT_FILTERED_QRY = "SELECT COUNT(computer.id) FROM computer LEFT JOIN company on company.id = computer.company_id WHERE LOWER(computer.name) LIKE :computerName OR LOWER(company.name) LIKE :companyName OR introduced LIKE :introduced OR discontinued LIKE :discontinued";
	private static final String CREATE_QRY = "insert into computer (id, name, introduced, discontinued, company_id) values (:id,:name, :introduced,:discontinued,:companyId)";
	private static final String DELETE_QRY = "delete from computer where id = :id";
	public static final String DELETE_WITH_COMP_QRY = "delete from computer where company_id = :companyId";
	private static final String UPDATE_QRY = "update computer set name = :name, introduced = :introduced, discontinued = :discontinued , company_id = :companyId where id = :id";
	private static final String FIND_BY_ID_QRY = "SELECT computer.id as computer_id, computer.name as computer_name, computer.introduced, computer.discontinued, computer.company_id, company.name as company_name FROM computer LEFT JOIN company on company.id=computer.company_id WHERE computer.id= :id";
	private static final String FIND_BY_NAME_QRY = "SELECT computer.id as computer_id, computer.name as computer_name, computer.introduced, computer.discontinued, computer.company_id, company.name as company_name FROM computer LEFT JOIN company on company.id=computer.company_id WHERE computer.name= :name";
	private static final String LIST_QRY = "SELECT computer.id as computer_id, computer.name as computer_name, computer.introduced, computer.discontinued, computer.company_id, company.name as company_name FROM computer LEFT JOIN company on company.id = computer.company_id ";
	private static final String FILTER_QRY = "WHERE LOWER(computer.name) LIKE :computerName OR  LOWER(company.name) LIKE :companyName OR introduced LIKE :introduced OR discontinued LIKE :discontinued";
	private static final String PAGINATE_QRY =  " LIMIT :limit OFFSET :offset ";
	private static final String ORDER_QRY =  "ORDER BY";

	private NamedParameterJdbcTemplate jdbcTemplate;

	@Autowired
	public ComputerDAO(NamedParameterJdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public boolean create(Computer obj) {
		obj.validate();
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("id", obj.getId());
		params.addValue("name", obj.getName());
		params.addValue("introduced", obj.getIntroduced());
		params.addValue("discontinued", obj.getDiscontinued());
		params.addValue("companyId", obj.getCompany().getId());
		jdbcTemplate.update(CREATE_QRY, params);
		return true;
	}

	@Override
	public boolean delete(Long id) {
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("id", id);
		jdbcTemplate.update(DELETE_QRY, params);
		return true;
	}

	@Override
	public boolean update(Computer obj) {
		
		obj.validate();
		Computer computer = this.findById(obj.getId());
		computer.validate();

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("name", obj.getName(), Types.VARCHAR);

		if(obj.getIntroduced() != null && computer.getDiscontinued() != null ) {
			if(obj.getIntroduced().isBefore(computer.getDiscontinued())) {
				params.addValue("introduced", Date.valueOf(obj.getIntroduced()), Types.DATE);
			} else {
				throw new ComputerValidatorException.DateValidator();
			}

		} else if(computer.getIntroduced() != null) {
			params.addValue("introduced", Date.valueOf(computer.getIntroduced()), Types.DATE);
		} else {
			params.addValue("introduced", null, Types.DATE);
		}

		if(obj.getDiscontinued() != null && computer.getIntroduced() != null) {
			if(obj.getDiscontinued().isAfter(computer.getIntroduced())) {
				params.addValue("discontinued", obj.getDiscontinued(), Types.DATE);
			} else {
				throw new ComputerValidatorException.DateValidator();
			}
		} else if(computer.getDiscontinued() != null) {
			params.addValue("discontinued", Date.valueOf(computer.getDiscontinued()), Types.DATE);
		} else {
			params.addValue("discontinued", null, Types.DATE);
		}

		if(obj.getCompany() != null && obj.getCompany().getId() != null)  {
			params.addValue("companyId", obj.getCompany().getId(), Types.BIGINT);
		} else if(computer.getCompany() != null && computer.getCompany().getId() != null) {
			params.addValue("companyId", computer.getCompany().getId() , Types.BIGINT);
		} else {
			params.addValue("companyId", null, Types.BIGINT);
		}

		params.addValue("id", computer.getId(), Types.BIGINT);
		jdbcTemplate.update(UPDATE_QRY, params);
		return true;
	}

	@Override
	public Computer findById(Long id) {
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("id", id);
		Computer computer = jdbcTemplate.queryForObject(
				FIND_BY_ID_QRY, params, new ComputerRowMapper());   
		return computer;
	}

	@Override
	public Computer findByName(String name) {
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("name", name);
		Computer computer = jdbcTemplate.queryForObject(
				FIND_BY_NAME_QRY, params, new ComputerRowMapper());   
		return computer;
	}

	@Override
	public List<Computer> list() {
		List<Computer> list = jdbcTemplate.query(
				LIST_QRY, new ComputerRowMapper());   
		return list;
	}

	@Override
	public int getNumberRows(){
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		int count = jdbcTemplate.queryForObject(COUNT_QRY, parameters,  Integer.class);
		return count;
	}

	public int getNumberRowsFiltered(String filter) {
		filter =  "%"+filter+"%";
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("computerName", filter);
		params.addValue("companyName", filter);
		params.addValue("introduced", filter);
		params.addValue("discontinued", filter);
		int count = jdbcTemplate.queryForObject(
				COUNT_FILTERED_QRY, params, Integer.class);  
		return count;
	}

	public List<Computer> listByPage(int offset, int rows) {
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("limit", rows);
		params.addValue("offset", offset);
		List<Computer> list = jdbcTemplate.query(
				LIST_QRY + PAGINATE_QRY, params, new ComputerRowMapper());  
		return list;
	}

	public List<Computer> listFiltered(int offset, int rows, String filter) {
		filter =  "%"+filter+"%";
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("limit", rows);
		params.addValue("offset", offset);
		params.addValue("computerName", filter);
		params.addValue("companyName", filter);
		params.addValue("introduced", filter);
		params.addValue("discontinued", filter);
		List<Computer> list = jdbcTemplate.query(
				LIST_QRY +FILTER_QRY +PAGINATE_QRY, params, new ComputerRowMapper());  
		return list;

	}

	public List<Computer> listOrdered(int offset, int rows, String column, boolean ascOrder) {
		String order = ascOrder ? "ASC" : "DESC";
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("limit", rows);
		params.addValue("offset", offset);
		List<Computer> list = jdbcTemplate.query(
				LIST_QRY + ORDER_QRY + " " +column +" " +order +" " + PAGINATE_QRY, params, new ComputerRowMapper());  
		return list;
	}

	public List<Computer> listOrderedAndFiltered(int offset, int rows, String filter, String column, boolean ascOrder) {
		String order = ascOrder ? "ASC" : "DESC";
		MapSqlParameterSource params = new MapSqlParameterSource();
		filter =  "%"+filter+"%";
		params.addValue("limit", rows);
		params.addValue("offset", offset);
		params.addValue("computerName", filter);
		params.addValue("companyName", filter);
		params.addValue("introduced", filter);
		params.addValue("discontinued", filter);
		params.addValue("limit", rows);
		params.addValue("offset", offset);
		List<Computer> list = jdbcTemplate.query(
				LIST_QRY + FILTER_QRY + ORDER_QRY + " " + column +" " +order +" " + PAGINATE_QRY, params, new ComputerRowMapper());  
		return list;
	}

}