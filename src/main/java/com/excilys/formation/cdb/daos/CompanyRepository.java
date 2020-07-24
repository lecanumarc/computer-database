package com.excilys.formation.cdb.daos;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.excilys.formation.cdb.pojos.Company;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long>{

	List<Company> findByNameContaining(String string);
}	