package com.excilys.formation.cdb.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.formation.cdb.pojos.Computer;
import com.excilys.formation.cdb.services.ComputerDaoProvider;


@WebServlet("/addComputer")
public class AddComputer extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		int id = Integer.parseInt(req.getParameter("id"));
//		Computer computer = ComputerDaoProvider.getInstance().findById(id);
//		System.out.println(computer);
//		String computername = request.getParameter("computerName");
//		String introduced = request.getParameter("introduced");
//		String companyId = request.getParameter("companyId");
//		System.out.println(computername);
//		System.out.println(introduced);
//		System.out.println(companyId); 
		request.getRequestDispatcher("views/addComputer.jsp").forward(request, response);
		
	}
	
//	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
////		int id = Integer.parseInt(req.getParameter("id"));
////		Computer computer = ComputerDaoProvider.getInstance().findById(id);
////		System.out.println(computer);
//		req.getRequestDispatcher("views/addComputer.jsp").forward(req, res);
//	}
	
}
