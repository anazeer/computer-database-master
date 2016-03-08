package com.excilys.cdb.test;

import static org.junit.Assert.assertNotNull;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.excilys.cdb.model.Company;
import com.excilys.cdb.persistence.CompanyDAO;
import com.excilys.cdb.persistence.ConnectionSingleton;

public class CompanyDAOTest {

	private static Connection conn;
	private static CompanyDAO companyDAO;
	
	@BeforeClass
	public static void init() {
		conn = ConnectionSingleton.getInstance();
		companyDAO = new CompanyDAO();
	}
	
	@AfterClass
	public static void end() throws SQLException {
		conn.close();
		conn = null;
	}
	
	/**
	 * We assume that the database is not empty and contains at least 500 keys from 1 to 500
	 * @throws Exception
	 */
	@Test
	public void testFind() throws Exception {
		Company computer = companyDAO.findById(10L);
		assertNotNull(computer);
	}
	
	@Test
	public void testFindAll() throws Exception {
		List<Company> list = companyDAO.findAll();
		assertNotNull(list);
		assert(list.size() > 0);
	}

}
