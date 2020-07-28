package com.excilys.formation.cdb.services;

import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.excilys.formation.cdb.daos.ComputerRepository;
import com.excilys.formation.cdb.models.Computer;

@Service
public class ComputerDaoProvider {

	public ComputerRepository instanceDAO;
	private EntityManagerFactory emf;
	private EntityManager em;

	@Autowired
	public ComputerDaoProvider(ComputerRepository computerDao, EntityManagerFactory emf) {
		this.instanceDAO = computerDao;
		this.emf = emf;
		this.em = emf.createEntityManager();
	}

	public void add(Computer obj) {
		em.getTransaction().begin();
		em.persist(obj);
		em.getTransaction().commit();
	}

	public void delete(long id) {
		Computer computer =  em.find(Computer.class, id);
		em.getTransaction().begin();
		em.remove(computer);
		em.getTransaction().commit();
	}

	public void edit(Computer obj) {
		em.getTransaction().begin();
		em.merge(obj);
		em.getTransaction().commit();
	}

	public Optional<Computer> findById(long id) {
		return instanceDAO.findById(id);
	}

	public int getNumberRows() {
		return (int) instanceDAO.count();
	}

	public Page<Computer> listByPage(int index, int rows) {
		return instanceDAO.findAll(PageRequest.of(index, rows));
	}

	public Page<Computer> listOrderedAndFiltered(int index, int rows, String filter, String column, boolean ascOrder) {
		return instanceDAO.findByNameContaining(filter, PageRequest.of(index, rows , sortBy(column, ascOrder)));
	}

	private Sort sortBy(String column, boolean ascOrder) {
		if(ascOrder) {
			return Sort.by(column).ascending();
		}
		return Sort.by(column).descending();
	}

	public Page<Computer> listOrdered(int index, int rows, String column, boolean ascOrder) {
		return instanceDAO.findAll(PageRequest.of(index, rows , sortBy(column, ascOrder)));
	}

	public Page<Computer> listFiltered(int index, int rows, String filter) {
		return instanceDAO.findByNameContaining(filter, PageRequest.of(index, rows));
	}

}
