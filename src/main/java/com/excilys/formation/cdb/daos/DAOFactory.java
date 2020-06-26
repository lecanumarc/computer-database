package com.excilys.formation.cdb.daos;

public class DAOFactory {

	/**
	 * Retourne un objet interagissant avec la BDD
	 * @return DAO
	 */
	public static ComputerDAO getComputerDAO(){
		return new ComputerDAO(false);
	}

	/**
	 * Retourne un objet interagissant avec la BDD
	 * @return DAO
	 */
	public static CompanyDAO getCompanyDAO(){
		return new CompanyDAO(false);
	}

}