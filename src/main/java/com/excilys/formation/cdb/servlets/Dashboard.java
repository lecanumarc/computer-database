package com.excilys.formation.cdb.servlets;

import java.io.IOException;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.excilys.formation.cdb.pojos.Company;
import com.excilys.formation.cdb.pojos.Computer;
import com.excilys.formation.cdb.services.ComputerDaoProvider;

import java.lang.Math;
import java.time.LocalDate; 
/**
 * Servlet implementation class Dashboard
 */
@WebServlet("/aaa")
public class Dashboard extends HttpServlet {
//	private static final long serialVersionUID = 1L;
//
//	/**
//	 * @see HttpServlet#HttpServlet()
//	 */
//
//	private int queryRows = 20;//	Number of computer to print
//	private double maxPage = 1;	
//	private int queryOffset = 1;	
//	private int currentPage = 1;	
//	private int rowNumber = 0;
//	private String filter = null;
//	private String column = null;
//	private boolean ascOrder = false;
//	private List<Computer> computerList = null;
//	
//	@Autowired
//	ComputerDaoProvider computerDaoProvider;
//
//	@Override
//	public void init(ServletConfig config) throws ServletException {
//		super.init(config);
//		SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, config.getServletContext());
//	}
//	/**
//	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
//	 */
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//
//		if(request.getParameter("queryRows") != null) {
//			queryRows = Integer.parseInt(request.getParameter("queryRows"));
//		}
//		if(request.getParameter("currentPage") != null) {
//			currentPage = Integer.parseInt(request.getParameter("currentPage"));
//			queryOffset = currentPage * queryRows;
//		}
//		else {
//			currentPage = 0;
//			queryOffset = 1;
//		}
//
//		if(request.getParameter("search") != null && !request.getParameter("search").trim().isEmpty()) {
//			filter = request.getParameter("search").trim();
//			rowNumber = computerDaoProvider.getNumberRowsFiltered(filter);
//
//			if(request.getParameter("columnOrder") != null && !request.getParameter("columnOrder").isEmpty()) {
//				request.setAttribute("columnOrder", request.getParameter("columnOrder"));
//				setOrder(request, response);
//				computerList = computerDaoProvider.listOrderedAndFiltered(queryOffset - 1, queryRows, filter, column, ascOrder);
//			} else {
//				computerList = computerDaoProvider.listFiltered(queryOffset - 1, queryRows, filter);
//			}
//		} else {
//			filter = null;
//			if(request.getParameter("columnOrder") != null && !request.getParameter("columnOrder").isEmpty()) {
//				request.setAttribute("columnOrder", request.getParameter("columnOrder"));
//				setOrder(request, response);
//				computerList = computerDaoProvider.listOrdered(queryOffset - 1, queryRows, column, ascOrder);
//			} else {
//				computerList = computerDaoProvider.listByPage(queryOffset - 1, queryRows);
//				rowNumber = computerDaoProvider.getNumberRows();
//			}
//		}
//
//		maxPage = Math.ceil((rowNumber/(double)queryRows));
//		request.setAttribute("maxPage", maxPage);
//		request.setAttribute("currentPage", currentPage);
//		request.setAttribute("queryOffset", queryOffset);
//		request.setAttribute("queryRows", queryRows);
//		request.setAttribute("computerList", computerList);
//		request.setAttribute("rowNumber", rowNumber);
//		if(filter != null) {
//			request.setAttribute("filter", filter);
//		}
//		request.getRequestDispatcher("views/dashboard.jsp").forward(request, response);
//	}
//
//	protected void setOrder(HttpServletRequest request, HttpServletResponse response) {
//		if(column != null && column.contentEquals(request.getParameter("columnOrder"))) {
//			ascOrder = ! ascOrder;
//		} else {
//			column = request.getParameter("columnOrder");
//			ascOrder = true;
//		}
//		request.setAttribute("columnOrder", column);
//	}

}
