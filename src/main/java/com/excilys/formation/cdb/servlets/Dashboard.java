package com.excilys.formation.cdb.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.formation.cdb.pojos.Company;
import com.excilys.formation.cdb.pojos.Computer;
import com.excilys.formation.cdb.services.ComputerDaoProvider;

import java.lang.Math;
import java.time.LocalDate; 
/**
 * Servlet implementation class Dashboard
 */
@WebServlet("/dashboard")
public class Dashboard extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */

	private int queryRows = 20;//	Number of computer to print
	private double maxPage = 1;	
	private int queryOffset = 1;	
	private int currentPage = 1;	
	private ArrayList<Computer> computerList = null;
	ComputerDaoProvider daoProvider;

	public Dashboard() {
		super();
		daoProvider = ComputerDaoProvider.getInstance();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(request.getParameter("queryRows") != null) {
			queryRows = Integer.parseInt(request.getParameter("queryRows"));
		}
		if(request.getParameter("currentPage") != null) {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
			queryOffset = currentPage * queryRows;
		}
		else {
			currentPage = 0;
			queryOffset = 1;
		}

		int rowNumber = daoProvider.getNumberRows();
		maxPage = Math.ceil((rowNumber/(double)queryRows));
		
		if(request.getParameter("search") != null && !request.getParameter("search").trim().isEmpty()) {
			String filter = request.getParameter("search").trim();;
			if(request.getParameter("columnFiltered") != null && !request.getParameter("search").isEmpty()) {
				filterList(request, response, filter);
			} else {
				computerList = daoProvider.listFiltered(queryOffset - 1, queryRows, filter);
			}
		} else {
			computerList = daoProvider.listByPage(queryOffset - 1, queryRows);
		}

		request.setAttribute("maxPage", maxPage);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("queryOffset", queryOffset);
		request.setAttribute("queryRows", queryRows);
		request.setAttribute("computerList", computerList);
		request.setAttribute("rowNumber", rowNumber);

		request.getRequestDispatcher("views/dashboard.jsp").forward(request, response);
	}

	protected void filterList(HttpServletRequest request, HttpServletResponse response, String filter) throws ServletException, IOException {
		String column = request.getParameter("columnFiltered");
		String order = request.getParameter("order") != null && !request.getParameter("order").isEmpty() ? request.getParameter("order") : "ASC";
		computerList = daoProvider.listOrdered(queryOffset - 1, queryRows, filter, column, order);
		
		request.setAttribute("computerList", computerList);

		request.getRequestDispatcher("views/dashboard.jsp").forward(request, response);
	}

}
