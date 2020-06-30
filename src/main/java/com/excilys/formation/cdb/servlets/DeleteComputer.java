package com.excilys.formation.cdb.servlets;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.formation.cdb.services.ComputerDaoProvider;

@WebServlet("/deleteComputer")
public class DeleteComputer extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ComputerDaoProvider daoProvider = ComputerDaoProvider.getInstance();
		if(request.getParameter("selection") != null && !request.getParameter("selection").isEmpty()) {
			List<String> idList = Arrays.asList(request.getParameter("selection").split(","));
			for(String id : idList) {
				Long idLong = Long.parseLong(id);
				daoProvider.delete(idLong);
			}
		}
		response.sendRedirect(request.getContextPath() + "/dashboard");
	}

}
