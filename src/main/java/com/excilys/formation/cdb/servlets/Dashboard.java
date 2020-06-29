package com.excilys.formation.cdb.servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.formation.cdb.pojos.Computer;
import com.excilys.formation.cdb.services.ComputerDaoProvider;

import java.lang.Math; 
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
	private int currentPage = 1;	
	private int queryOffset = 1;	
	private int pageIterator = 1;	
	
    public Dashboard() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//	get request parameters
		if(request.getParameter("queryRows") != null) {
			queryRows = Integer.parseInt(request.getParameter("queryRows"));
		}
		if(request.getParameter("pageIterator") != null) {
			pageIterator = Integer.parseInt(request.getParameter("pageIterator"));
			queryOffset = pageIterator * queryRows;
		}
		else {
			pageIterator = 0;
			queryOffset = 1;
		}
		
		ComputerDaoProvider daoProvider = ComputerDaoProvider.getInstance();
		ArrayList<Computer> computerList = daoProvider.listByPage(queryOffset - 1, queryRows);
		int rowNumber = daoProvider.getNumberRows();
		maxPage = Math.ceil((rowNumber/(double)queryRows));
		
		request.setAttribute("maxPage", maxPage);
		request.setAttribute("pageIterator", pageIterator);
		request.setAttribute("queryOffset", queryOffset);
		request.setAttribute("queryRows", queryRows);

		System.out.println("currentPage : "+currentPage);
		System.out.println("pageIterator : "+pageIterator);
		System.out.println("queryOffset : "+queryOffset);
		System.out.println("queryRows : "+queryRows);
		System.out.println("maxPage : "+maxPage);
		System.out.println("-------------------- : ");
		
		request.setAttribute("computerList", computerList);
		request.setAttribute("rowNumber", rowNumber);
		request.getRequestDispatcher("views/dashboard.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
//	 */
//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		// TODO Auto-generated method stub
//		doGet(request, response);
//	}

}
