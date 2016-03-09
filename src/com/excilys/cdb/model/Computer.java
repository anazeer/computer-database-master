package com.excilys.cdb.model;

import java.util.Date;

/**
 * Computer model
 * @author excilys
 *
 */
public class Computer {
	
	private Long id;
	private String name;
	private Date introduced;
	private Date discontinued;
	private Long company_id;

	public Computer() {

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

	public Date getIntroduced() {
		return introduced;
	}

	public void setIntroduced(Date introduced) {
		this.introduced = introduced;
	}

	public Date getDiscontinued() {
		return discontinued;
	}

	public void setDiscontinued(Date discontinued) {
		this.discontinued = discontinued;
	}

	public Long getCompany_id() {
		return company_id;
	}

	public void setCompany_id(Long company_id) {
		this.company_id = company_id;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(this == obj) {
			return true;
		}
		if(obj == null) {
			return false;
		}
		if(getClass() != obj.getClass()) {
			return false;
		}
		Computer other = (Computer) obj;
		return id.equals(other.id)
				&& name.equals(other.name) 
				&& introduced.equals(other.introduced)
				&& discontinued.equals(other.discontinued)
				&& company_id.equals(other.company_id);
	}
	
	@Override
	public int hashCode() {
		return super.hashCode();
	}
	
	@Override
	public String toString() {
		return name + " (id : " + id + ")";
	}
	
	public String toDetailedString() {
		String result = toString() + "\n";
		result += introduced != null ? "Introduced : " + introduced +"\n" : "";
		result += discontinued != null ? "Discontinued : " + discontinued + "\n" : "";
		result += company_id != null ? "Company_id : " + company_id + "\n" : "";
		return result;
	}
}
