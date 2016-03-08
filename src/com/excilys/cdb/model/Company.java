package com.excilys.cdb.model;

/**
 * Company model
 * @author excilys
 *
 */
public class Company {
	
	private long id;
	private String name;

	public Company() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
