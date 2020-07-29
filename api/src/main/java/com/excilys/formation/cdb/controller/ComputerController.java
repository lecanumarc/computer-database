package com.excilys.formation.cdb.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.excilys.formation.cdb.model.Computer;
import com.excilys.formation.cdb.service.ComputerDaoProvider;

@RestController
@RequestMapping({"/computer"})
public class ComputerController {
	ComputerDaoProvider computerDaoProvider;

	@Autowired
	public ComputerController(ComputerDaoProvider computerDaoProvider) {
		this.computerDaoProvider = computerDaoProvider;
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Computer> getComputerById(@PathVariable("id") long id) {
		Optional<Computer> Computer = computerDaoProvider.findById(id);
		if(Computer.isPresent()) {
			return new ResponseEntity<Computer>(Computer.get(), HttpStatus.OK);
		}
		return new ResponseEntity<Computer>(HttpStatus.NOT_FOUND);
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<Computer> deleteComputer(@PathVariable("id") long id) {
		computerDaoProvider.delete(id);
		return new ResponseEntity<Computer>(HttpStatus.OK);
	}
}
