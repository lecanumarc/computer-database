package com.excilys.formation.cdb.daos;

import java.sql.Types;
import java.util.List;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.excilys.formation.cdb.mapper.CompanyRowMapper;
import com.excilys.formation.cdb.models.Company;

@Repository
public class CompanyDAO implements DAO<Company> {

	private static final String CREATE_QRY = "insert into company value (:id, :name)";
	private static final String DELETE_QRY = "delete from company where id = :id";
	private static final String UPDATE_QRY  = "update company set name = :name where id = :id";
	private static final String FIND_BY_ID_QRY = "SELECT id as company_id, name as company_name FROM company WHERE id = :id"; 
	private static final String FIND_BY_NAME_QRY = "SELECT id as company_id, name as company_name FROM company WHERE name = :name"; 
	private static final String LIST_QRY = "SELECT id as company_id, name as company_name  FROM company"; 
	private static final String COUNT_QRY = "SELECT COUNT(id) FROM company";
	private static final String PAGINATED_LIST_QRY =  "SELECT id as company_id, name as company_name FROM company LIMIT :limit OFFSET :offset";

	NamedParameterJdbcTemplate jdbcTemplate;

	private static Logger logger = LoggerFactory.getLogger(ComputerDAO.class);

	@Autowired
	public CompanyDAO(NamedParameterJdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public boolean create(Company obj) {
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("id", obj.getId());
		params.addValue("name", obj.getName());
		jdbcTemplate.update(CREATE_QRY, params);
		return true;
	}

	//@Transactional
	@Override
	public boolean delete(Long id) {
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("company_id", id);
		jdbcTemplate.update(ComputerDAO.DELETE_WITH_COMP_QRY, parameters);
		MapSqlParameterSource parametersCompany = new MapSqlParameterSource();
		parametersCompany.addValue("id", id);
		jdbcTemplate.update(DELETE_QRY,parametersCompany);
		return true;
	}

	@Override
	public boolean update(Company obj) {
		Company company = this.findById(obj.getId());
		MapSqlParameterSource params = new MapSqlParameterSource();
		if(obj.getName() != null && !obj.getName().isEmpty()) {
			params.addValue("name", obj.getName(), Types.VARCHAR);
		} else {
			params.addValue("name", company.getName(), Types.VARCHAR);
		}
		params.addValue("id", obj.getId());
		jdbcTemplate.update(UPDATE_QRY, params);
		return true;
	}

	@Override
	public Company findById(Long id) {
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("id", id);
		Company company  = jdbcTemplate.queryForObject(
				FIND_BY_ID_QRY, params, new CompanyRowMapper());   
		return company;
	}

	@Override
	public Company findByName(String name) {
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("name", name);
		Company company  = jdbcTemplate.queryForObject(
				FIND_BY_NAME_QRY, params, new CompanyRowMapper());   
		return company;
	}

	@Override
	public List<Company> list() {
		List<Company> list = jdbcTemplate.query(
				LIST_QRY, new CompanyRowMapper());   
		return list;
	}

	public List<Company> listByPage(int offset, int rows) {
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("limit", rows);
		params.addValue("offset", offset);
		List<Company> list = jdbcTemplate.query(
				PAGINATED_LIST_QRY, new CompanyRowMapper());  
		return list;
	}

	public int getNumberRows() {
		MapSqlParameterSource params = new MapSqlParameterSource();
		int count = jdbcTemplate.queryForObject(COUNT_QRY, params, Integer.class);
		return count;
	}

}