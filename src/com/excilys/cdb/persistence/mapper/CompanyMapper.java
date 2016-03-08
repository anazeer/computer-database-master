package com.excilys.cdb.persistence.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.excilys.cdb.model.Company;

/**
 * 
 * @author excilys
 *
 */
public class CompanyMapper implements DAOMapper<Company> {

	public CompanyMapper() {
		super();
	}

	@Override
	public Company find(ResultSet result) {
		try {
			if(result.next()) {
				Company company = new Company();
				company.setId(result.getLong("id"));
				company.setName(result.getString("name"));
				return company;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Company> findAll(ResultSet result) {
		List<Company> companyList = new ArrayList<Company>();
		try {
			while(result.next()) {
				Company company = new Company();
				company.setId(result.getLong("id"));
				company.setName(result.getString("name"));
				companyList.add(company);
				return companyList;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
