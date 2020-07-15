package com.excilys.formation.cdb.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.excilys.formation.cdb.dto.CompanyDto;
import com.excilys.formation.cdb.dto.ComputerDto;
import com.excilys.formation.cdb.mapper.ComputerMapper;
import com.excilys.formation.cdb.pojos.Company;
import com.excilys.formation.cdb.pojos.Computer;
import com.excilys.formation.cdb.services.CompanyDaoProvider;
import com.excilys.formation.cdb.services.ComputerDaoProvider;
import com.excilys.formation.cdb.validator.ComputerValidator;

/**
 * Servlet implementation class EditComputer
 */
@WebServlet("/editComputer")
public class EditComputer extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Autowired
	CompanyDaoProvider companyDaoProvider; 
	@Autowired
	ComputerDaoProvider computerDaoProvider; 

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, config.getServletContext());
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<Company> companyList = companyDaoProvider.listCompanies();
		request.setAttribute("companyList", companyList);

		request.setAttribute("id", request.getParameter("id"));
		Long id =  Long.parseLong(request.getParameter("id"));
		Computer computerToUpdate = computerDaoProvider.findById(id);
		request.setAttribute("computerToUpdate", computerToUpdate);
		request.getRequestDispatcher("views/editComputer.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		CompanyDto companyDto = new CompanyDto(Long.parseLong(request.getParameter("companyId")));
		ComputerDto computerDto = new ComputerDto(request.getParameter("computerName"),
				request.getParameter("introduced"),
				request.getParameter("discontinued"),
				companyDto);
		computerDto.setId((Long.parseLong(request.getParameter("id"))));
		try {
			Computer computer = ComputerMapper.DtoToComputer(computerDto);
			computerDaoProvider.edit(computer);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		response.sendRedirect(request.getContextPath() + "/dashboard");
	}
}
