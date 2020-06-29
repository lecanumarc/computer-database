package com.excilys.formation.cdb.pojos;

public class Company {

	Long id;
	String name;

	public Company() {
		super();
	}

	public Company(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		if(name != null) {
			this.name = name;
		}
	}

	@Override
	public String toString() {
		return "Company [id=" + id + ", name=" + name + "]";
	}


}
