package com.excilys.formation.cdb.tools;

public class Menu {

	public static enum MessageType{
		ASK_COMPUTER_ID,
		GOOD_BYE,
		MAIN_MENU,
		NAVIGUATION,
		ASK_COMPANY_ID,
		ASK_COMPUTER_DETAILS 
	}
	
	private static final String MAIN_MENU_MSG = "- List computers (1) \n"
			+ "- List companies (2) \n"
			+ "- Show computer details (3) \n"
			+ "- Create a computer (4) \n"
			+ "- Update a computer (5) \n"
			+ "- Delete a computer (6) \n"
			+ "- Delete a company (7) \n"
			+ "- Quit (q) \n";
	
	private static final String ASK_COMPUTER_ID_MSG = "\nPlease enter computer Id \n";
	private static final String GOOD_BYE_MSG = "\n Goodbye ...\n ";
	private static final String ASK_COMPANY_ID_MSG = "\n Please enter company Id  ...\n ";
	private static final String NAVIGUATION_MSG = "\n (n) next page \t (p) previous page \t (m) main menu \t or enter page number. \n";
	private static final String ASK_COMPUTER_DETAILS_MSG = "\n (n) next page \t (p) previous page \t (m) main menu \t or enter page number. \n";
	 
	public static String getMessage(MessageType type) {
		String str = "";
		switch(type) {
		case ASK_COMPUTER_DETAILS:
			str += ASK_COMPUTER_DETAILS_MSG;
			break;
		case ASK_COMPUTER_ID:
			str += ASK_COMPUTER_ID_MSG;
			break;
		case GOOD_BYE:
			str += GOOD_BYE_MSG;
			break;
		case NAVIGUATION:
			str += NAVIGUATION_MSG;
			break;
		case MAIN_MENU:
			str += MAIN_MENU_MSG;
			break;
		case ASK_COMPANY_ID:
			str += ASK_COMPANY_ID_MSG;
			break;
		default:
			break;
		}
		return str;
	}
}
