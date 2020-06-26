package com.excilys.formation.cdb.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.formation.cdb.pojos.Computer;
import com.excilys.formation.cdb.services.ComputerDaoProvider;


@WebServlet("/test")
public class FindComputerServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("id"));
		Computer computer = ComputerDaoProvider.getInstance().findById(id);
		System.out.println(computer);
		req.getRequestDispatcher("views/test.jsp").forward(req, res);
	}
}
