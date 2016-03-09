package com.excilys.cdb.test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import com.excilys.cdb.model.Company;
import com.excilys.cdb.persistence.CompanyDAO;

public class CompanyDAOTest {

	private static CompanyDAO companyDAO;
	
	@BeforeClass
	public static void init() {
		companyDAO = new CompanyDAO();
	}

	/**
	 * We assume that the database is not empty and contains at least 500 keys from 1 to 500
	 * @throws Exception
	 */
	@Test
	public void testFind() throws Exception {
		Company computer = companyDAO.findById(1000L);
		assertNotNull(computer);
	}
	
	@Test
	public void testFindAll() throws Exception {
		List<Company> list = companyDAO.findAll();
		assertNotNull(list);
		assert(list.size() > 0);
		//assertEquals(list.size(), 42);
	}
}
