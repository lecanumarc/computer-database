package com.excilys.formation.cdb.daos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.excilys.formation.cdb.mapper.ComputerMapper;
import com.excilys.formation.cdb.pojos.Computer;
import com.excilys.formation.cdb.services.ConnectionH2;
import com.excilys.formation.cdb.services.ConnectionMySQL;
import com.excilys.formation.cdb.services.Connector;

public class ComputerDAO  {

	private static final String CREATE_QRY = "insert into computer (id, name, introduced, discontinued, company_id) values (?,?,?,?,?)";
	private static final String DELETE_QRY = "delete from computer where id = (?)";
	private static final String UPDATE_QRY = "update computer set name = ?, introduced = ?, discontinued = ? where id = ?";
	private static final String FIND_BY_ID_QRY = "SELECT id, name, introduced, discontinued, company_id FROM computer WHERE id = ?"; 
	private static final String FIND_BY_NAME_QRY = "SELECT id, name, introduced, discontinued, company_id FROM computer WHERE name = ?"; 
	private static final String LIST_QRY = "SELECT id, name, introduced, discontinued, company_id FROM computer"; 
	private static final String COUNT_QRY = "SELECT COUNT(*) as var FROM computer";
	private static final String PAGINATED_LIST_QRY =  "SELECT id, name, introduced, discontinued, company_id FROM computer LIMIT ? OFFSET ? ";

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
			if(obj.getIntroDate() == null) {
				st.setNull(3, java.sql.Types.DATE);
			} else {
				st.setDate(3, java.sql.Date.valueOf(obj.getIntroDate()));
			}
			if(obj.getDiscDate() == null) {
				st.setNull(4, java.sql.Types.DATE);
			} else {
				st.setDate(4, java.sql.Date.valueOf(obj.getDiscDate())); 
			}
			if(obj.getCompanyId() == 0) {
				st.setNull(5, java.sql.Types.INTEGER);
			} else {
				st.setInt(5, obj.getCompanyId());
			}
			st.executeUpdate();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean delete(int id) {
		try (Connection connect = connector.getInstance();
				PreparedStatement st = connect.prepareStatement(DELETE_QRY)){
			st.setInt(1, id);
			int count = st.executeUpdate();
			System.out.println(count +" computer row(s) deleted.");
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean update(Computer obj) {
		Computer computer = this.findById(obj.getId());
		try (Connection connect = connector.getInstance(); 
				PreparedStatement st = connect.prepareStatement(UPDATE_QRY)){
			if(obj.getName().isEmpty()) {
				st.setString(1, computer.getName());
			} else {
				st.setString(1, obj.getName());
			}

			if(obj.getIntroDate() != null) {
				st.setDate(2, java.sql.Date.valueOf(obj.getIntroDate()));
			} else if(computer.getIntroDate() != null) {
				st.setDate(2, java.sql.Date.valueOf(computer.getIntroDate()));
			} else {
				st.setNull(2, java.sql.Types.DATE);
			}

			if(obj.getDiscDate() != null) {
				st.setDate(3, java.sql.Date.valueOf(obj.getDiscDate()));
			} else if(computer.getDiscDate() != null) {
				st.setDate(3, java.sql.Date.valueOf(computer.getDiscDate()));
			} else {
				st.setNull(3, java.sql.Types.DATE);
			}

			st.setInt(4, obj.getId());
			st.executeUpdate();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public Computer findById(int id) {
		Computer computer = new Computer();      
		try (Connection connect = connector.getInstance();
				PreparedStatement st = connect.prepareStatement(FIND_BY_ID_QRY)){
			st.setInt(1, id);
			ResultSet result = st.executeQuery();
			if(result.next())
				computer = ComputerMapper.map(result);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return computer;
	}

	public Computer findByName(String name) {
		Computer computer = new Computer();      
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