package com.excilys.formation.cdb.servlets;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.excilys.formation.cdb.services.ComputerDaoProvider;

@WebServlet("/zz")
public class DeleteComputer extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@Autowired
	ComputerDaoProvider computerDaoProvider;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, config.getServletContext());
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("selection") != null && !request.getParameter("selection").isEmpty()) {
			List<String> idList = Arrays.asList(request.getParameter("selection").split(","));
			for(String id : idList) {
				Long idLong = Long.parseLong(id);
				computerDaoProvider.delete(idLong);
			}
		}
		response.sendRedirect(request.getContextPath() + "/dashboard");
	}

}
