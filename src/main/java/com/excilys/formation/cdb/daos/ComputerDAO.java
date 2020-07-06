package com.excilys.formation.cdb.daos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.formation.cdb.exceptions.ComputerValidatorException;
import com.excilys.formation.cdb.mapper.ComputerMapper;
import com.excilys.formation.cdb.pojos.Computer;
import com.excilys.formation.cdb.services.DbConnection;
import com.excilys.formation.cdb.services.Connector;

public class ComputerDAO  {

	//	sql query
	private static final String COUNT_QRY = "SELECT COUNT(*) as var FROM computer";
	private static final String COUNT_FILTERED_QRY = "SELECT COUNT(computer.id) as var FROM computer LEFT JOIN company on company.id = computer.company_id WHERE LOWER(computer.name) LIKE ? OR LOWER(company.name) LIKE ? OR introduced LIKE ? OR discontinued LIKE ?";
	private static final String CREATE_QRY = "insert into computer (id, name, introduced, discontinued, company_id) values (?,?,?,?,?)";
	private static final String DELETE_QRY = "delete from computer where id = (?)";
	public static final String DELETE_WITH_COMP_QRY = "delete from computer where company_id = (?)";
	private static final String UPDATE_QRY = "update computer set name = ?, introduced = ?, discontinued = ? , company_id = ? where id = ?";
	private static final String FIND_BY_ID_QRY = "SELECT computer.id as computer_id, computer.name as computer_name, computer.introduced, computer.discontinued, computer.company_id, company.name as company_name FROM computer LEFT JOIN company on company.id=computer.company_id WHERE computer.id=?";
	private static final String FIND_BY_NAME_QRY = "SELECT computer.id as computer_id, computer.name as computer_name, computer.introduced, computer.discontinued, computer.company_id, company.name as company_name FROM computer LEFT JOIN company on company.id=computer.company_id WHERE computer.name=?";
	private static final String LIST_QRY = "SELECT computer.id as computer_id, computer.name as computer_name, computer.introduced, computer.discontinued, computer.company_id, company.name as company_name FROM computer LEFT JOIN company on company.id = computer.company_id ";
	private static final String FILTER_QRY = "WHERE LOWER(computer.name) LIKE ? OR  LOWER(company.name) LIKE ? OR introduced LIKE ? OR discontinued LIKE ?";
	private static final String PAGINATE_QRY =  "LIMIT ? OFFSET ? ";
	private static final String ORDER_QRY =  "ORDER BY";

	private static Connector connector;
	private static ComputerDAO computerDAO;
	private static Logger logger = LoggerFactory.getLogger(ComputerDAO.class);

	public ComputerDAO() {
		connector = new DbConnection();
	}

	public boolean create(Computer obj) {
		try (Connection connect = connector.getInstance();
				PreparedStatement st = connect.prepareStatement(CREATE_QRY)){
			obj.validate();
			st.setNull(1, java.sql.Types.INTEGER);
			st.setString(2, obj.getName());
			if(obj.getintroduced() == null) {
				st.setNull(3, java.sql.Types.DATE);
			} else {
				st.setDate(3, java.sql.Date.valueOf(obj.getintroduced()));
			}
			if(obj.getdiscontinued() == null) {
				st.setNull(4, java.sql.Types.DATE);
			} else {
				st.setDate(4, java.sql.Date.valueOf(obj.getdiscontinued())); 
			}
			if(obj.getCompany() == null) {
				st.setNull(5, java.sql.Types.INTEGER);
			} else {
				st.setLong(5, obj.getCompany().getId());
			}
			st.executeUpdate();
			logger.info("Computer created deleted.");
			return true;
		} catch (Exception e) {
			logger.error("Error during computer creation : " +e.getMessage());
		}
		return false;
	}

	public boolean delete(Long id) {
		try (Connection connect =  connector.getInstance();
				PreparedStatement st = connect.prepareStatement(DELETE_QRY)){
			st.setLong(1, id);
			int count = st.executeUpdate();
			logger.debug(count +" computer row(s) deleted.");
			return true;
		} catch (SQLException e) {
			logger.error("Error during computer deletion : " +e.getMessage());
		}
		return false;
	}

	public boolean update(Computer obj) {
		obj.validate();
		Computer computer = this.findById(obj.getId());
		computer.validate();
		try (Connection connect =  connector.getInstance(); 
				PreparedStatement st = connect.prepareStatement(UPDATE_QRY)){
			if(obj.getName().isEmpty()) {
				st.setString(1, computer.getName());
			} else {
				st.setString(1, obj.getName());
			}

			if(obj.getintroduced() != null && computer.getdiscontinued() != null ) {
				if(obj.getintroduced().isBefore(computer.getdiscontinued())) {
					st.setDate(2, java.sql.Date.valueOf(obj.getintroduced()));
				} else {
					throw new ComputerValidatorException.DateValidator();
				}

			} else if(computer.getintroduced() != null) {
				st.setDate(2, java.sql.Date.valueOf(computer.getintroduced()));
			} else {
				st.setNull(2, java.sql.Types.DATE);
			}

			if(obj.getdiscontinued() != null && computer.getintroduced() != null) {
				if(obj.getdiscontinued().isAfter(computer.getintroduced())) {
					st.setDate(3, java.sql.Date.valueOf(obj.getdiscontinued()));
				} else {
					throw new ComputerValidatorException.DateValidator();
				}
			} else if(computer.getdiscontinued() != null) {
				st.setDate(3, java.sql.Date.valueOf(computer.getdiscontinued()));
			} else {
				st.setNull(3, java.sql.Types.DATE);
			}

			if(obj.getCompany() != null && obj.getCompany().getId() != null)  {
				st.setLong(4, obj.getCompany().getId());
			} else if(computer.getCompany() != null && computer.getCompany().getId() != null) {
				st.setLong(4, computer.getCompany().getId());
			} else {
				st.setLong(4, java.sql.Types.INTEGER);
			}

			st.setLong(5, obj.getId());
			st.executeUpdate();
			logger.info("Computer updated.");
			return true;
		} catch (Exception e) {
			logger.error("Error during computer update : " +e.getMessage());
		}
		return false;
	}

	public Computer findById(Long id) {
		Computer computer = null;      
		try (Connection connect =  connector.getInstance();
				PreparedStatement st = connect.prepareStatement(FIND_BY_ID_QRY)){
			st.setLong(1, id);
			ResultSet result = st.executeQuery();
			if(result.next()) {
				computer = ComputerMapper.map(result);
			}
		} catch (SQLException e) {
			logger.error("Error during computer find by id : " +e.getMessage());
		}
		logger.info("Computer found by id.");
		return computer;
	}

	public Computer findByName(String name) {
		Computer computer = null;      
		try (Connection connect =  connector.getInstance();
				PreparedStatement st = connect.prepareStatement(FIND_BY_NAME_QRY)){
			st.setString(1, name);
			ResultSet result = st.executeQuery();
			if(result.next())
				computer = ComputerMapper.map(result);
		} catch (SQLException e) {
			logger.error("Error during computer find by name : " +e.getMessage());

		}
		logger.info("Computer found by name.");
		return computer;
	}

	public ArrayList<Computer> list() {
		ArrayList<Computer> list = new ArrayList<Computer>();
		try (Connection connect =  connector.getInstance();
				Statement st = connect.createStatement()){
			ResultSet result = st.executeQuery(LIST_QRY);
			while(result.next()) {
				list.add(ComputerMapper.map(result));
			}    
		} catch (SQLException e) {
			logger.error("Error during computer listing : " +e.getMessage());
		}

		logger.info(list.size() +"computer(s) listed.");
		return list;
	}

	public int getNumberRows() {
		int count = 0;
		try (Connection connect =  connector.getInstance();
				Statement st = connect.createStatement()){
			ResultSet result = st.executeQuery(COUNT_QRY);
			if(result.next()) {
				count = result.getInt("var");
			}
		} catch (SQLException e) {
			logger.error("Error during computer counting : " +e.getMessage());

		}
		logger.info(count +"computer(s) rows(s) in database.");
		return count;
	}

	public int getNumberRowsFiltered(String filter) {
		int count = 0;
		try (Connection connect =  connector.getInstance();
				PreparedStatement st = connect.prepareStatement(COUNT_FILTERED_QRY)){
			filter =  "%"+filter+"%";
			st.setString(1, filter);
			st.setString(2, filter);
			st.setString(3, filter);
			st.setString(4, filter);
			ResultSet result = st.executeQuery();
			if(result.next()) {
				count = result.getInt("var");
			}
		} catch (SQLException e) {
			logger.error("Error during computer counting with filter : " +e.getMessage());

		}

		logger.info(count +"computer(s) listed.");
		return count;
	}

	public ArrayList<Computer> listByPage(int offset, int rows) {
		ArrayList<Computer> list = new ArrayList<Computer>();
		try (Connection connect =  connector.getInstance();
				PreparedStatement st = connect.prepareStatement(LIST_QRY + PAGINATE_QRY)){
			st.setInt(1, rows);
			st.setInt(2, offset);
			ResultSet result = st.executeQuery();
			while(result.next()) {
				list.add(ComputerMapper.map(result));
			}    
		} catch (SQLException e) {
			logger.error("Error during computer listing by page : " +e.getMessage());
		}
		logger.info(list.size() +"computer(s) listed.");
		return list;
	}

	public ArrayList<Computer> listFiltered(int offset, int rows, String filter) {
		ArrayList<Computer> list = new ArrayList<Computer>();
		try (Connection connect =  connector.getInstance();
				PreparedStatement st = connect.prepareStatement(LIST_QRY +FILTER_QRY +PAGINATE_QRY)){
			// set filter
			filter =  "%"+filter+"%";
			st.setString(1, filter);
			st.setString(2, filter);
			st.setString(3, filter);
			st.setString(4, filter);
			//	pagination
			st.setInt(5, rows);
			st.setInt(6, offset);

			ResultSet result = st.executeQuery();
			while(result.next()) {
				list.add(ComputerMapper.map(result));
			}    
		} catch (SQLException e) {
			logger.error("Error during computer listing with filter : " +e.getMessage());

		}
		logger.info(list.size() +"computer(s) listed.");
		return list;
	}

	public ArrayList<Computer> listOrdered(int offset, int rows, String column, boolean ascOrder) {
		ArrayList<Computer> list = new ArrayList<Computer>();
		String order = ascOrder ? "ASC" : "DESC";
		try (Connection connect =  connector.getInstance();
				PreparedStatement st = connect.prepareStatement(LIST_QRY + ORDER_QRY + " " + column +" " +order +" " + PAGINATE_QRY)){
			//	pagination
			st.setInt(1, rows);
			st.setInt(2, offset);

			ResultSet result = st.executeQuery();
			while(result.next()) {
				list.add(ComputerMapper.map(result));
			}    
		} catch (SQLException e) {
			logger.error("Error during computer lsiting with order : " +e.getMessage());
		}
		logger.info(list.size() +"computer(s) listed.");
		return list;
	}

	public ArrayList<Computer> listOrderedAndFiltered(int offset, int rows, String filter, String column, boolean ascOrder) {
		ArrayList<Computer> list = new ArrayList<Computer>();
		String order = ascOrder ? "ASC" : "DESC";
		try (Connection connect =  connector.getInstance();
				PreparedStatement st = connect.prepareStatement(LIST_QRY + FILTER_QRY + ORDER_QRY + " " + column +" " +order +" " + PAGINATE_QRY)){
			// set filter
			filter =  "%"+filter+"%";
			st.setString(1, filter);
			st.setString(2, filter);
			st.setString(3, filter);
			st.setString(4, filter);
			//	pagination
			st.setInt(5, rows);
			st.setInt(6, offset);

			ResultSet result = st.executeQuery();
			while(result.next()) {
				list.add(ComputerMapper.map(result));
			}    
		} catch (SQLException e) {
			logger.error("Error during computer listing with order and filter : " +e.getMessage());
		}
		logger.info(list.size() +"computer(s) listed.");
		return list;
	}

}