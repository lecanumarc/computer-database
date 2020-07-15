package com.excilys.formation.cdb.services;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DbConnection { 
	
	@Autowired
	private DataSource ds;
	
	private static Connection connect;
	private static Logger logger = LoggerFactory.getLogger(DbConnection.class);
	
	public Connection getConnection() throws SQLException {
		if(connect == null || connect.isClosed()) {
			try {
				connect = ds.getConnection();
			} catch(SQLException eSQL) {
				logger.error("Error connection to DB",eSQL);
			}
		}
		return connect;
	}
}