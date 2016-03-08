package com.excilys.cdb.model;

/**
 * Company model
 * @author excilys
 *
 */
public class Company {
	
	private Long id;
	private String name;

	public Company() {
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
		this.name = name;
	}
	
}
