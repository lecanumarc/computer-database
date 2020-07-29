package com.excilys.formation.cdb.tool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class Page {

	/*
	 Page class, containing your entities and the page information.
	 */
	final Logger logger = LoggerFactory.getLogger(Page.class);
	private String title;
	private int number;
	private String content; // content by lines
	
	public Page() {
	}
	
	public Page(String title, int number, String content) {
		super();
		this.title = title;
		this.number = number;
		this.content = content;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	public void addContent(String content) {
		this.content += content;
	}
	
	public void presentPage() {
		logger.info("\n ------ " + this.getTitle() +" ------");
		logger.info(this.content);
		logger.info("------ "+this.number + " ------ ");
		logger.info(this.content);
	}

	@Override
	public String toString() {
		return "Page [title=" + title + ", number=" + number + "]";
	}
	
}
