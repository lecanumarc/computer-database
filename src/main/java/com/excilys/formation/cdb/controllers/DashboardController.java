package com.excilys.formation.cdb.controllers;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.excilys.formation.cdb.pojos.Computer;
import com.excilys.formation.cdb.services.ComputerDaoProvider;

@Controller
@RequestMapping({ "/", "/dashboard"})
public class DashboardController {

	private int queryNb = 20; //	nb of rows to query
	private double maxPage = 1;	
	private int queryOffset = 1;	
	private int pageIndex = 1;	
	private int rowNumber = 0;
	private String filter = null;
	private String column = null;
	private boolean ascOrder = false;
	private List<Computer> computerList = null;

	ComputerDaoProvider computerDaoProvider;

	@Autowired
	public DashboardController(ComputerDaoProvider computerDaoProvider) {
		this.computerDaoProvider = computerDaoProvider;
	}

	@GetMapping
	public String listComputers(@RequestParam(required=false, name="queryRows") String queryRows,
			@RequestParam(required=false, name="currentPage") String currentPage,
			@RequestParam(required=false, name="search") String search,
			@RequestParam(required=false, name="columnOrder") String columnOrder,
			@RequestParam(required=false, name="search") String searchRequest,
			Model model) {

		if(queryRows != null) {
			queryNb = Integer.parseInt(queryRows);
		}
		if(currentPage != null) {
			pageIndex = Integer.parseInt(currentPage);
			queryOffset = pageIndex * queryNb;
		}
		else {
			pageIndex = 0;
			queryOffset = 1;
		}

		if(search != null && !search.trim().isEmpty()) {
			filter = search.trim();
			rowNumber = computerDaoProvider.getNumberRowsFiltered(filter);

			if(columnOrder != null && !columnOrder.isEmpty()) {
				model.addAttribute("columnOrder", columnOrder);
				setOrder(columnOrder, model);
				computerList = computerDaoProvider.listOrderedAndFiltered(queryOffset - 1, queryNb, filter, column, ascOrder);
			} else {
				computerList = computerDaoProvider.listFiltered(queryOffset - 1, queryNb, filter);
			}
		} else {
			filter = null;
			if(columnOrder != null && !columnOrder.isEmpty()) {
				model.addAttribute("columnOrder", columnOrder);
				model.addAttribute("columnOrder", columnOrder);
				computerList = computerDaoProvider.listOrdered(queryOffset - 1, queryNb, column, ascOrder);
			} else {
				computerList = computerDaoProvider.listByPage(queryOffset - 1, queryNb);
				rowNumber = computerDaoProvider.getNumberRows();
			}
		}

		maxPage = Math.ceil((rowNumber/(double)queryNb));
		model.addAttribute("maxPage", maxPage);
		model.addAttribute("currentPage", pageIndex);
		model.addAttribute("queryOffset", queryOffset);

		model.addAttribute("computerList", computerList);
		model.addAttribute("rowNumber", rowNumber);
		if(filter != null) {
			model.addAttribute("filter", filter);
		}

		return "dashboard";
	}

	@PostMapping
	public String deleteComputers(@RequestParam(name="selection") String selection) {
		List<Long> idList = Stream.of(selection.split(","))
				.map(Long::parseLong)
				.collect(Collectors.toList());
		idList.stream().forEach(id->computerDaoProvider.delete(id));
		return "redirect:/dashboard";
	}

	protected void setOrder(String columnOrder, Model model) {
		if(column != null && column.contentEquals(columnOrder)) {
			ascOrder = ! ascOrder;
		} else {
			column = columnOrder;
			ascOrder = true;
		}
		model.addAttribute("columnOrder", column);
	}


}
