package com.excilys.formation.cdb.daos;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.excilys.formation.cdb.models.Computer;

@Repository
public interface ComputerRepository extends JpaRepository<Computer, Long>{

	Page<Computer> findByNameContaining(String string, Pageable page);
	
}