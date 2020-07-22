package com.excilys.formation.cdb.daos;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.excilys.formation.cdb.pojos.Company;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long>{
	Optional<Company> findById(Long id);
}	