package com.excilys.formation.cdb.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
	
	@GetMapping(value = "/list")
	public ResponseEntity<List<Computer>> getComputerList() {
		List<Computer> list = computerDaoProvider.listComputers();
		return new ResponseEntity<List<Computer>>(list, HttpStatus.OK);
	}

	@GetMapping(value = "page/{index}/{rows}")
	public ResponseEntity<List<Computer>> getComputerPage(@PathVariable("index") int index, @PathVariable("rows") int rows) {
		Page<Computer> computers = computerDaoProvider.listByPage(index, rows);
		return new ResponseEntity<List<Computer>>(computers.getContent(), HttpStatus.OK);
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<Computer> deleteComputer(@PathVariable("id") long id) {
		computerDaoProvider.delete(id);
		return new ResponseEntity<Computer>(HttpStatus.OK);
	}
	
	@PostMapping(value = "/add", consumes  = MediaType.APPLICATION_JSON_VALUE,  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Computer> updateComputer(@RequestBody Computer computer) {
		computerDaoProvider.add(computer);
		return new ResponseEntity<Computer>(HttpStatus.OK);
	}
}
