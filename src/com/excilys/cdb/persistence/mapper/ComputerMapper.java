package com.excilys.cdb.persistence.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.excilys.cdb.model.Computer;

public class ComputerMapper implements DAOMapper<Computer> {

	public ComputerMapper() {
		super();
	}
	
	@Override
	public Computer find(ResultSet result) {
		try {
			Computer computer = new Computer();
			computer.setId(result.getLong("id"));
			computer.setName(result.getString("name"));
			computer.setIntroduced(result.getDate("introduced"));
			computer.setDiscontinued(result.getDate("discontinued"));
			computer.setCompany_id(result.getLong("company_id"));
			return computer;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<Computer> findAll(ResultSet result) {
		List<Computer> computerList = new ArrayList<Computer>();
		try {
			while(result.next()) {
				Computer computer = new Computer();
				computer.setId(result.getLong("id"));
				computer.setName(result.getString("name"));
				computer.setIntroduced(result.getDate("introduced"));
				computer.setDiscontinued(result.getDate("discontinued"));
				computer.setCompany_id(result.getLong("company_id"));
				computerList.add(computer);
				return computerList;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
