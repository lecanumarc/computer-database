package com.excilys.formation.cdb.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.excilys.formation.cdb.dto.ComputerDto;
import com.excilys.formation.cdb.mapper.ComputerMapper;
import com.excilys.formation.cdb.pojos.Computer;
import com.excilys.formation.cdb.services.ComputerDaoProvider;

@Controller
@RequestMapping({"/", "/dashboard"})
public class DashboardController {

	private int queryNb = 20; //	nb of rows to query
	private double maxPage = 1;	
	private int pageIndex = 0;	
	private long rowNumber = 0;
	private String filter = null;
	private String column = null;
	private boolean ascOrder = false;
	private Page<Computer> computerList = null;
	private List<ComputerDto> computerDtoList = null;

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
			Model model) {

		if(queryRows != null) {
			queryNb = Integer.parseInt(queryRows);
		}
		if(currentPage != null) {
			pageIndex = Integer.parseInt(currentPage) - 1;
		}
		else {
			pageIndex = 0;
		}


		if(search != null) {
			filter = search.trim();
		}
		if(columnOrder != null && !columnOrder.isEmpty()) {
			setOrder(columnOrder, model);
		}

		if(filter != null) {
			if(column != null) {
				computerList = computerDaoProvider.listOrderedAndFiltered(pageIndex, queryNb, filter, column, ascOrder);
			} else {
				computerList = computerDaoProvider.listFiltered(pageIndex,  queryNb, filter);
			}
		} else {
			if(column != null) {
				computerList = computerDaoProvider.listOrdered(pageIndex, queryNb, column, ascOrder);
			} else {
				computerList = computerDaoProvider.listByPage(pageIndex, queryNb);
			}
		}

		computerDtoList = ComputerMapper.getComputerDtoList(computerList);
		maxPage = computerList.getTotalPages();
		rowNumber = computerList.getTotalElements();

		model.addAttribute("maxPage", maxPage);
		model.addAttribute("currentPage", pageIndex+1);

		model.addAttribute("computerList", computerDtoList);
		model.addAttribute("rowNumber", rowNumber);
		if(filter != null) {
			model.addAttribute("filter", filter);
		}

		return "dashboard";
	}

	@PostMapping
	public String deleteComputers(@RequestParam(name="selection") String selection) {
		//		List<Long> idList = Stream.of(selection.split(","))
		//				.map(Long::parseLong)
		//				.collect(Collectors.toList());
		//		idList.stream().forEach(id->computerDaoProvider.delete(id));
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
