package com.excilys.formation.cdb.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.excilys.formation.cdb.model.Company;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long>{
	Page<Company> findByNameContaining(String string, Pageable page);
}	
