package com.excilys.formation.cdb.services;

import java.util.ArrayList;

import com.excilys.formation.cdb.daos.CompanyDAO;
import com.excilys.formation.cdb.daos.DAOFactory;
import com.excilys.formation.cdb.pojos.Company;

public class CompanyDaoProvider {

		public static CompanyDAO instanceDAO;
		public static CompanyDaoProvider instance;

		private CompanyDaoProvider() {
			instanceDAO = DAOFactory.getCompanyDAO();

		}

		public static CompanyDaoProvider getInstance() {
			if(instance == null) {
				instance = new CompanyDaoProvider();
			}
			return instance;
		}
		
		public boolean add(Company obj) {
			return instanceDAO.create(obj);
		}

		public boolean edit(Company obj) {
			return instanceDAO.update(obj);
		}

		public Company findById(Long id) {
			return instanceDAO.findById(id);
		}

		public int getNumberRows() {
			return instanceDAO.getNumberRows();
		}

		public ArrayList<Company> listByPage(int offset, int rows) {
			return instanceDAO.listByPage(offset, rows);
		}
		
		public ArrayList<Company> listCompanies() {
			return instanceDAO.list();
		}

}
