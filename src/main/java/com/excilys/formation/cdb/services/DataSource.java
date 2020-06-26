package com.excilys.formation.cdb.services;

import java.sql.Connection;
import java.sql.SQLException;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class DataSource {
	private static HikariConfig config = new HikariConfig("src/main/resources/hikari-datasource.properties");
	private static HikariDataSource ds = new HikariDataSource(config);
	private static Connection connect;
	private DataSource() {}

	public static Connection getConnection() throws SQLException {
		if(connect == null) {
			try {
				connect = ds.getConnection();
			} catch (SQLException sqlException) {
				for (Throwable e : sqlException) {
					e.printStackTrace();
				}

			}
		}
		return connect;
	}
}
