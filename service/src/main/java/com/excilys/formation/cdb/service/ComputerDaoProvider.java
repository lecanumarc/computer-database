package com.excilys.formation.cdb.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.excilys.formation.cdb.dao.ComputerRepository;
import com.excilys.formation.cdb.model.Computer;

@Service
public class ComputerDaoProvider {

	public ComputerRepository computerDao;

	@Autowired
	public ComputerDaoProvider(ComputerRepository computerDao) {
		this.computerDao = computerDao;
	}

	public Computer add(Computer obj) {
		return computerDao.save(obj);
	}

	public void delete(long id) {
		computerDao.deleteById(id);
	}

	public Computer edit(Computer obj) {
		return computerDao.save(obj);
	}

	public Optional<Computer> findById(long id) {
		return computerDao.findById(id);
	}
	
	public List<Computer> listByCompanyId(long id) {
		return computerDao.findAllByCompanyId(id);
	}

	public int getNumberRows() {
		return (int) computerDao.count();
	}

	public Page<Computer> listByPage(int index, int rows) {
		return computerDao.findAll(PageRequest.of(index, rows));
	}

	public Page<Computer> listOrderedAndFiltered(int index, int rows, String filter, String column, boolean ascOrder) {
		return computerDao.findByNameContaining(filter, PageRequest.of(index, rows , sortBy(column, ascOrder)));
	}

	private Sort sortBy(String column, boolean ascOrder) {
		if(ascOrder) {
			return Sort.by(column).ascending();
		}
		return Sort.by(column).descending();
	}

	public Page<Computer> listOrdered(int index, int rows, String column, boolean ascOrder) {
		return computerDao.findAll(PageRequest.of(index, rows , sortBy(column, ascOrder)));
	}

	public Page<Computer> listFiltered(int index, int rows, String filter) {
		return computerDao.findByNameContaining(filter, PageRequest.of(index, rows));
	}

}
