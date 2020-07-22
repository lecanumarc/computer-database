package com.excilys.formation.cdb.daos;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.excilys.formation.cdb.pojos.Computer;

@Repository
public interface ComputerRepository extends JpaRepository<Computer, Long>{

	Optional<Computer> findById(Long id);
	List<Computer> findAll();
	
}