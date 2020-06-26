package com.excilys.formation.cdb.services;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileReader {

	public static ArrayList<String> readFile(String fileName) {
		ArrayList<String> lines = new ArrayList<String>();
		try {
		      File myObj = new File(fileName);
		      Scanner myReader = new Scanner(myObj);
		      while (myReader.hasNextLine()) {
		        lines.add(myReader.nextLine());
		      }
		      myReader.close();
		    } catch (FileNotFoundException e) {
		      e.printStackTrace();
		    }
		return lines;
	}
	
}
