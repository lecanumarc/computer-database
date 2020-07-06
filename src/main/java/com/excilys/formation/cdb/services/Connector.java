package com.excilys.formation.cdb.services;

import java.sql.Connection;
import java.sql.SQLException;

public interface Connector {
	
	public abstract Connection getInstance() throws SQLException;

	public abstract void close();
	
}
