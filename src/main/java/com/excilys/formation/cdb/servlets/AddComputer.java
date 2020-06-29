package com.excilys.formation.cdb.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.formation.cdb.pojos.Company;
import com.excilys.formation.cdb.services.CompanyDaoProvider;


@WebServlet("/addComputer")
public class AddComputer extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<Company> companyList = CompanyDaoProvider.getInstance().listCompanies();
		request.setAttribute("companyList", companyList);
		System.out.println(companyList);
		PrintWriter out = response.getWriter();
		out.println(companyList);
		request.getRequestDispatcher("views/addComputer.jsp").forward(request, response);
	
	}
	
//	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
////		int id = Integer.parseInt(req.getParameter("id"));
////		Computer computer = ComputerDaoProvider.getInstance().findById(id);
////		System.out.println(computer);
//		req.getRequestDispatcher("views/addComputer.jsp").forward(req, res);
//	}
	
}
