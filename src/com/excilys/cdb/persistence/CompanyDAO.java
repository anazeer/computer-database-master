package com.excilys.cdb.persistence;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.excilys.cdb.model.Company;
import com.excilys.cdb.persistence.mapper.CompanyMapper;
import com.excilys.cdb.persistence.mapper.DAOMapper;

/**
 * Company DAO implementation
 * @author excilys
 *
 */
public class CompanyDAO implements DAO<Company> {
	
	private Connection conn;

	/**
	 * ComputerDAO new instance for Computer type object persistence
	 */
	public CompanyDAO() {
		conn = ConnectionSingleton.getInstance();
	}

	@Override
	public List<Company> findAll() {
		List<Company> listCompany = null;
		try {
			Statement stmt = conn.createStatement();
			String query = "SELECT * FROM company";
			ResultSet result = stmt.executeQuery(query);
			DAOMapper<Company> mapper = new CompanyMapper();
			listCompany = new ArrayList<Company>();
			while(result.next())
				listCompany.add(mapper.find(result));
			result.close();
			stmt.close();
			return listCompany;
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Company findById(Long id) {
		Company company = null;
		try {
			Statement stmt = conn.createStatement();
			String query = "SELECT * FROM company WHERE company.id = " + id;
			ResultSet result = stmt.executeQuery(query);
			DAOMapper<Company> mapper = new CompanyMapper();
			if(result.next()) {
				company = mapper.find(result);
			}
			result.close();
			stmt.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return company;
	}

}
