package com.excilys.formation.cdb.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.excilys.formation.cdb.mapper.CompanyMapper;
import com.excilys.formation.cdb.pojos.Company;
import com.excilys.formation.cdb.services.DbConnection;

@Repository
public class CompanyDAO {

	private static final String CREATE_QRY = "insert into company value (?,?)";
	private static final String DELETE_QRY = "delete from company where id = (?)";
	private static final String UPDATE_QRY  = "update company set name = ? where id = ?";
	private static final String FIND_BY_ID_QRY = "SELECT id, name FROM company WHERE id = ?"; 
	private static final String FIND_BY_NAME_QRY = "SELECT id, name FROM company WHERE name = ?"; 
	private static final String LIST_QRY = "SELECT id, name FROM company"; 
	private static final String COUNT_QRY = "SELECT COUNT(*) as var FROM company";
	private static final String PAGINATED_LIST_QRY =  "SELECT id, name FROM computer LIMIT ? OFFSET ? ";

	@Autowired
	private DbConnection connector;

	public boolean create(Company obj) {
		try(Connection connect = connector.getConnection(); 
				PreparedStatement st = connect.prepareStatement(CREATE_QRY)){
			st.setLong(1, obj.getId());
			st.setString(2, obj.getName());
			int count = st.executeUpdate();
			System.out.println(count +" company row(s) created.");
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean delete(Long id) throws SQLException {
		try(Connection connect = connector.getConnection(); 
				PreparedStatement st1 = connect.prepareStatement(ComputerDAO.DELETE_WITH_COMP_QRY);
				PreparedStatement st2 = connect.prepareStatement(DELETE_QRY);){
			connect.setAutoCommit(false);
			st1.setLong(1,id);
			st2.setLong(1,id);
			int res1 = st1.executeUpdate();
			int res2 = st2.executeUpdate();

			if(res1 >= 0 && res2 == 1) {
				connect.commit();
			} else if(res2 == 0) {
				connect.rollback();
			}
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean update(Company obj) {
		Company company = this.findById(obj.getId());
		try(Connection connect = connector.getConnection(); 
				PreparedStatement st = connect.prepareStatement(UPDATE_QRY)){
			if(obj.getName().isEmpty()) {
				st.setString(1, company.getName());
			} else {
				st.setString(1, obj.getName());
			}

			st.setLong(2, obj.getId());
			int count = st.executeUpdate();
			System.out.println(count +" company row(s) updated.");
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public Company findById(Long id) {
		Company company = new Company();      
		try(Connection connect = connector.getConnection();
				PreparedStatement st = connect.prepareStatement(FIND_BY_NAME_QRY)){
			st.setLong(1, id);
			ResultSet result = st.executeQuery();
			if(result.next()) {
				company = CompanyMapper.map(result);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return company;
	}

	public Company findByName(String name) {
		Company company = new Company();      
		try(Connection connect = connector.getConnection(); 
				PreparedStatement st = connect.prepareStatement(FIND_BY_NAME_QRY)){
			st.setString(1, name);
			ResultSet result = st.executeQuery();
			if(result.next()) {
				company = CompanyMapper.map(result);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return company;
	}

	public ArrayList<Company> list() {
		ArrayList<Company> list = new ArrayList<Company>();
		try(Connection connect = connector.getConnection(); 
				Statement st = connect.createStatement()){
			ResultSet result = st.executeQuery(LIST_QRY);
			while(result.next()) {
				list.add(CompanyMapper.map(result));
			}    
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}

	public ArrayList<Company> listByPage(int offset, int rows) {
		ArrayList<Company> list = new ArrayList<Company>();
		try(Connection connect = connector.getConnection(); 
				PreparedStatement st = connect.prepareStatement(PAGINATED_LIST_QRY)){
			st.setInt(1, rows);
			st.setInt(2, offset);
			ResultSet result = st.executeQuery();
			while(result.next()) {
				list.add(CompanyMapper.map(result));
			}    
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public int getNumberRows() {
		int count = 0;
		try(Connection connect = connector.getConnection(); 
				Statement st = connect.createStatement()){
			ResultSet result = st.executeQuery(COUNT_QRY);
			if(result.next())
				count = result.getInt("var");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}

}