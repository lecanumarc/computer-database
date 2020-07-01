package com.excilys.formation.cdb.daos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.excilys.formation.cdb.exceptions.ComputerValidatorException;
import com.excilys.formation.cdb.mapper.ComputerMapper;
import com.excilys.formation.cdb.pojos.Computer;
import com.excilys.formation.cdb.services.ConnectionH2;
import com.excilys.formation.cdb.services.ConnectionMySQL;
import com.excilys.formation.cdb.services.Connector;

public class ComputerDAO  {

	private static final String CREATE_QRY = "insert into computer (id, name, introduced, discontinued, company_id) values (?,?,?,?,?)";
	private static final String DELETE_QRY = "delete from computer where id = (?)";
	private static final String UPDATE_QRY = "update computer set name = ?, introduced = ?, discontinued = ? , company_id = ? where id = ?";
	private static final String FIND_BY_ID_QRY = "SELECT computer.id as computer_id, computer.name as computer_name, computer.introduced, computer.discontinued, computer.company_id, company.name as company_name FROM computer LEFT JOIN company on company.id=computer.company_id WHERE computer.id=?";
	private static final String FIND_BY_NAME_QRY = "SELECT computer.id as computer_id, computer.name as computer_name, computer.introduced, computer.discontinued, computer.company_id, company.name as company_name FROM computer LEFT JOIN company on company.id=computer.company_id WHERE computer.name=?";
	private static final String LIST_QRY = "SELECT computer.id as computer_id, computer.name as computer_name, computer.introduced, computer.discontinued, computer.company_id, company.name as company_name FROM computer LEFT JOIN company on company.id=computer.company_id ";
	private static final String COUNT_QRY = "SELECT COUNT(*) as var FROM computer";
	private static final String PAGINATED_LIST_QRY =  "SELECT computer.id as computer_id, computer.name as computer_name, computer.introduced, computer.discontinued, computer.company_id, company.name as company_name FROM computer LEFT JOIN company on company.id=computer.company_id LIMIT ? OFFSET ? ";

	private static Connector connector;

	public ComputerDAO(boolean h2) {
		if(h2) {
			connector =  new ConnectionH2();
		} else {
			connector =  new ConnectionMySQL();
		}
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
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean delete(Long id) {
		try (Connection connect = connector.getInstance();
				PreparedStatement st = connect.prepareStatement(DELETE_QRY)){
			st.setLong(1, id);
			int count = st.executeUpdate();
			System.out.println(count +" computer row(s) deleted.");
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean update(Computer obj) {
		obj.validate();
		Computer computer = this.findById(obj.getId());
		computer.validate();
		try (Connection connect = connector.getInstance(); 
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
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public Computer findById(Long id) {
		Computer computer = null;      
		try (Connection connect = connector.getInstance();
				PreparedStatement st = connect.prepareStatement(FIND_BY_ID_QRY)){
			st.setLong(1, id);
			ResultSet result = st.executeQuery();
			if(result.next()) {
				computer = ComputerMapper.map(result);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return computer;
	}

	public Computer findByName(String name) {
		Computer computer = null;      
		try (Connection connect = connector.getInstance();
				PreparedStatement st = connect.prepareStatement(FIND_BY_NAME_QRY)){
			st.setString(1, name);
			ResultSet result = st.executeQuery();
			if(result.next())
				computer = ComputerMapper.map(result);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return computer;
	}

	public ArrayList<Computer> list() {
		ArrayList<Computer> list = new ArrayList<Computer>();
		try (Connection connect = connector.getInstance();
				Statement st = connect.createStatement()){
			ResultSet result = st.executeQuery(LIST_QRY);
			while(result.next()) {
				list.add(ComputerMapper.map(result));
			}    
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}

	public int getNumberRows() {
		int count = 0;
		try (Connection connect = connector.getInstance();
				Statement st = connect.createStatement()){
			ResultSet result = st.executeQuery(COUNT_QRY);
			if(result.next())
				count = result.getInt("var");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}

	public ArrayList<Computer> listByPage(int offset, int rows) {
		ArrayList<Computer> list = new ArrayList<Computer>();
		try (Connection connect = connector.getInstance();
				PreparedStatement st = connect.prepareStatement(PAGINATED_LIST_QRY)){
			st.setInt(1, rows);
			st.setInt(2, offset);
			ResultSet result = st.executeQuery();
			while(result.next()) {
				list.add(ComputerMapper.map(result));
			}    
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public void closeConnection() {
		connector.close();
	}


}