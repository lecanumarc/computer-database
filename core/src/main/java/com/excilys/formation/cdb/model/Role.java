package com.excilys.formation.cdb.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="user")
public class Role {
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	Long id;
	@Column(name="Name")
	String name;

	public Role() {
		super();
	}

	public Role(Long id, String Name) {
		super();
		this.id = id;
		this.name = Name;
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

	public void setName(String Name) {
		this.name = Name;
	}

	@Override
	public String toString() {
		return "Role [id=" + id + ", name=" + name + ", getId()=" + getId() + ", getName()="
				+ getName() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
	}


}
