package com.excilys.formation.cdb.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.excilys.formation.cdb.daos.UserRepository;
import com.excilys.formation.cdb.models.User;

public class UserDaoProvider implements UserDetailsService {

	public UserRepository userDao;

	@Autowired
	public UserDaoProvider(UserRepository userDao) {
		this.userDao = userDao;
	}

	public void add(User obj) {
		userDao.save(obj);
	}

	public void edit(User obj) {
		userDao.save(obj);
	}

	public void delete(long id) {
		userDao.deleteById(id);
	}

	public Optional<User> findById(Long id) {
		return userDao.findById(id);
	}

	public int getNumberRows() {
		return (int) userDao.count();
	}

	public Page<User> listByPage(int offset, int rows) {
		return userDao.findAll(PageRequest.of(offset, rows));
	}

	public List<User> listCompanies() {
		return userDao.findAll();
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> user = userDao.findByUsername(username);
		if(user.isPresent()) {
			return org.springframework.security.core.userdetails.User.builder()
					.username(user.get().getUsername())
					.password(user.get().getPassword())
					.roles(user.get().getRole().getName())
					.build();
		}else {

			throw new UsernameNotFoundException("username not found");
		}
	}

}
