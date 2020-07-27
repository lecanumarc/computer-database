package com.excilys.formation.cdb.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.excilys.formation.cdb.daos.ComputerRepository;
import com.excilys.formation.cdb.pojos.Computer;

@Service
public class ComputerDaoProvider {

	public ComputerRepository instanceDAO;

	@Autowired
	public ComputerDaoProvider(ComputerRepository computerDao) {
		this.instanceDAO = computerDao;
	}
	public Computer add(Computer obj) {
		return instanceDAO.save(obj);
	}

	public void delete(Computer obj) {
		instanceDAO.delete(obj);
	}

	public Computer edit(Computer obj) {
		return instanceDAO.save(obj);
	}

	public Optional<Computer> findById(Long id) {
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
