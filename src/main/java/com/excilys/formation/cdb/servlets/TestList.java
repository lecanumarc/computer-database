package com.excilys.formation.cdb.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.formation.cdb.dto.ComputerDto;
import com.excilys.formation.cdb.pojos.Computer;
import com.excilys.formation.cdb.services.ComputerDaoProvider;

/**
 * Servlet implementation class TestList
 */

@WebServlet("/test")
public class TestList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TestList() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		Long id = Long.parseLong(request.getParameter("id"));
//		Computer computer = ComputerDaoProvider.getInstance().findById(id);
//		PrintWriter out = response.getWriter();
//		out.println(computer);
		ComputerDaoProvider daoProvider = ComputerDaoProvider.getInstance();
		ArrayList<Computer> computerList = daoProvider.listByPage(5, 5);
		request.setAttribute("computerList", computerList);
		//request.getRequestDispatcher("views/test.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		ComputerDaoProvider daoProvider = ComputerDaoProvider.getInstance();
//		ArrayList<Computer> computerList = daoProvider.listByPage(5, 5);
//		request.setAttribute("computerList", computerList);
	}

}
