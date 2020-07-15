package com.excilys.formation.cdb.daos;

import java.util.List;
import javax.sql.DataSource;

public interface DAO<T> {

	/** 
	 * This is the method to be used to initialize
	 * database resources ie. connection.
	 */
	public void setDataSource(DataSource ds);

	/**
	 * M�thode de cr�ation
	 * @param obj
	 * @return boolean 
	 */
	public boolean create(T obj);

	/**
	 * M�thode pour effacer
	 * @param obj
	 * @return boolean 
	 */
	public boolean delete(Long id);

	/**
	 * M�thode de mise � jour
	 * @param obj
	 * @return boolean
	 */
	public boolean update(T obj);

	/**
	 * M�thode de recherche des informations
	 * @param id
	 * @return T
	 */
	public T findById(Long id);

	/**
	 * M�thode de recherche des informations
	 * @param id
	 * @return T
	 */
	public T findByName(String name);

	/**
	 * M�thode de recherche des informations
	 * @return int
	 */
	public int getNumberRows();

	/**
	 * M�thode de listing
	 * @param id
	 * @return T
	 */
	public List<T> list();

}