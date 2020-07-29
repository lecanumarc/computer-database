package com.excilys.formation.cdb.service;

import com.excilys.formation.cdb.model.Computer;
import com.excilys.formation.cdb.tool.Menu;
import com.excilys.formation.cdb.tool.Page;

public class Session {

//	private UserScanner userScanner;
//	private Page page;
//	private char userInput;
//	private UserCLI userCLI;
//	private State state = State.MainMenu;
//	private enum State{
//		MainMenu,
//		Browsing,
//		Other
//	}
//
//	public Session() {
//		userScanner = UserScanner.getInstance();
//		userCLI = UserCLI.getInstance();
//		page = new Page();
//	}
//
//	public void waitForEnterKey() {
//		try {
//			page.addContent("\n\nPress enter to continue \n");
//			page.presentPage();
//			userScanner.nextLine(); 
//		} catch (Exception e1) {
//			e1.printStackTrace();
//		}
//	}
//
//	public UserScanner getScanner() {
//		return userScanner;
//	}
//
//	public Page getPage() {
//		return page;
//	}
//
//	public void setPage(Page page) {
//		this.page = page;
//	}
//
//	public Page getMainMenuPage() {
//		Page page = new Page("Main Menu", 0, Menu.getMessage(Menu.MessageType.MAIN_MENU));
//		return page;
//	}
//
//	public Page getGoodbyePage() {
//		Page page = new Page("Logout", 0, Menu.getMessage(Menu.MessageType.GOOD_BYE));
//		return page;
//	}
//
//	public Page getComputerList(int offset, int rows, int elements) {
//		Page page = new Page("Computer list (" +elements +" computers)", offset/rows, 
//				userCLI.listComputersByPage(offset, rows) + ""+ Menu.getMessage(Menu.MessageType.NAVIGUATION));
//		return page;
//	}
//
//	public Page getCompanyList(int offset, int rows, int elements) {
//		Page page = new Page("Computer list" +elements +" companies)", offset/rows, 
//				userCLI.listCompanyByPage(offset, rows) + Menu.getMessage(Menu.MessageType.NAVIGUATION));
//		return page;
//	}
//
//	public void browseComputerList(int offset, int rows) {
//		boolean loop = true;
//		state = State.Browsing;
//		int elements = userCLI.getComputerRows();
//		page = getComputerList(offset, rows, elements);
//		page.presentPage();
//		while(loop) {
//
//			try {
//				userInput = userScanner.nextChar(); 
//			} catch (Exception e1) {
//				e1.printStackTrace();
//			}
//
//			switch(userInput) {
//			case 'n':	// next page
//				if(offset < (elements+ rows)) offset += rows;
//				page = getComputerList(offset, rows, elements);
//				page.presentPage();
//				break;
//			case 'p':	// previous page
//				if(offset >= rows) offset -= rows;
//				page = getComputerList(offset, rows, elements);
//				page.presentPage();
//				break;
//			case 'm':	// main menu
//				offset = 0;
//				state = State.MainMenu;
//				loop = false;
//				break;
//			default:
//				int pageIndex = userInput - '0';
//				if(pageIndex <= elements) offset = pageIndex * rows;
//				page = getComputerList(offset, rows, elements);
//				page.presentPage();
//				break;
//			}
//			try {
//				userScanner.nextLine();
//			} catch (Exception e2) {
//				e2.printStackTrace();
//			}
//		}
//	}
//
//	public void browseCompanyList(int offset, int rows) {
//		boolean loop = true;
//		state = State.Browsing;
//		int elements = userCLI.getComputerRows();
//		page = getCompanyList(offset, rows, elements);
//		page.presentPage();
//		while(loop) {
//
//			try {
//				userInput = userScanner.nextChar(); 
//			} catch (Exception e1) {
//				e1.printStackTrace();
//			}
//
//			switch(userInput) {
//			case 'n':	// next page
//				offset += rows;
//				page = getCompanyList(offset, rows, elements);
//				page.presentPage();
//				break;
//			case 'p':	// previous page
//				if(offset >= rows) offset -= rows;
//				page = getCompanyList(offset, rows, elements);
//				page.presentPage();
//				break;
//			case 'm':	// main menu
//				offset = 0;
//				state = State.MainMenu;
//				loop = false;
//				break;
//			default:
//				int pageIndex = userInput - '0';
//				if(pageIndex <= elements) offset = pageIndex * rows;
//				page = getComputerList(offset, rows, elements);
//				page.presentPage();
//				break;
//			}
//			try {
//				userScanner.nextLine();
//			} catch (Exception e2) {
//				e2.printStackTrace();
//			}
//		}
//	}
//
//	public void deleteComputer() {
//		state = State.Other;
//		page.addContent(Menu.getMessage(Menu.MessageType.ASK_COMPUTER_ID));
//		page.presentPage();
//		try {
//			Long id = new Long(userScanner.nextInt());
//			userCLI.deleteComputer(id);
//			page = new Page("Computer deletion", 0, "Computer " +id +" deleted.");
//			userScanner.nextLine(); //	Consume stored "\n"
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//	
//	public void deleteCompany() {
//		state = State.Other;
//		page.addContent(Menu.getMessage(Menu.MessageType.ASK_COMPANY_ID));
//		page.presentPage();
//		try {
//			Long id = new Long(userScanner.nextInt());
//			userCLI.deleteComputer(id);
//			page = new Page("Company deletion", 0, "Company " +id +" deleted.");
//			userScanner.nextLine(); //	Consume stored "\n"
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//
//	public void updateComputer() {
//		try {
//			userScanner.nextLine(); //	Consume stored "\n"
//		} catch (Exception e2) {
//			e2.printStackTrace();
//		} 
//		state = State.Other;
//		page.addContent(Menu.getMessage(Menu.MessageType.ASK_COMPUTER_DETAILS));
//		page.presentPage();
//		try {
//			Computer computer = userScanner.getComputer();
//			userCLI.updateComputer(computer);
//			page = new Page("Computer update", 0, computer.toString());
//
//		} catch (Exception e1) {
//			e1.printStackTrace();
//		}
//	}
//
//	public void createComputer() {
//		try {
//			userScanner.nextLine(); //	Consume stored "\n"
//		} catch (Exception e2) {
//			e2.printStackTrace();
//		} 
//		state = State.Other;
//		page.addContent(Menu.getMessage(Menu.MessageType.ASK_COMPUTER_DETAILS));
//		page.presentPage();
//		try {
//			Computer computer = userScanner.getComputer();
//			userCLI.createComputer(computer);
//			page = new Page("Computer creation", 0, computer.toString());
//
//		} catch (Exception e1) {
//			e1.printStackTrace();
//		}
//	}
//
//	public void showComputerDetails() {
//		state = State.Other;
//		page = new Page("Computer details", 1, Menu.getMessage(Menu.MessageType.ASK_COMPUTER_ID));
//		page.presentPage();
//		try {
//			Long id = new Long(userScanner.nextInt());
//			page = new Page("Computer details", 1, userCLI.showComputerDetails(id));
//			page.presentPage();
//			userScanner.nextLine(); //	Consume stored "\n"
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//
//	public void quit() {
//		page = getGoodbyePage();
//		page.presentPage();
//	}
//
//	public void run() {
//		boolean loop = true;
//		int offset = 0;
//		int rows = 5;
//		while(loop) {
//
//			if(state == State.Other) {
//				waitForEnterKey();
//				state = State.MainMenu;
//			}
//			if(state == State.MainMenu) {
//				page = getMainMenuPage();
//				page.presentPage();
//			}
//
//
//			try {
//				userInput = userScanner.nextChar(); 
//			} catch (Exception e1) {
//				e1.printStackTrace();
//			}
//
//			switch(userInput) {
//			case '1':	// List computers
//				browseComputerList(offset, rows);
//				break;
//			case '2':	// List companies
//				browseCompanyList(offset, rows);
//				break;
//			case '3':	// Show computer details
//				showComputerDetails();
//				break;
//			case '4':	// Create a computer
//				createComputer();
//				break;
//			case '5':	// Update a computer
//				updateComputer();
//				break;
//			case '6':	
//				deleteComputer();
//				break;
//			case '7':	
//				deleteCompany();
//				break;
//			case 'q':
//				quit();
//				loop = false;
//				break;
//			case 'Q':
//				quit();
//				loop = false;
//			default:
//				break;
//			}
//
//		}
//	}
//
//	public static void main(String[] args) {
//		Session session = new Session();
//		session.run();		
//	}
}
