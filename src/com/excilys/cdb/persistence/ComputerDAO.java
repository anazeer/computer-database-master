package com.excilys.cdb.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import com.excilys.cdb.model.Computer;
import com.excilys.cdb.persistence.mapper.ComputerMapper;
import com.excilys.cdb.persistence.mapper.DAOMapper;

/**
 * Computer DAO implementation
 * @author excilys
 *
 */
public class ComputerDAO implements DAO<Computer> {
	
	private Connection conn;

	/**
	 * ComputerDAO new instance for Computer type object persistence
	 */
	public ComputerDAO() {
		conn = ConnectionSingleton.getInstance();
	}
	
	@Override
	public List<Computer> findAll() {
		try {
			Statement stmt = conn.createStatement();
			String query = "SELECT * FROM computer";
			ResultSet result = stmt.executeQuery(query);
			DAOMapper<Computer> mapper = new ComputerMapper();
			List<Computer> listComputer = mapper.findAll(result);
			result.close();
			stmt.close();
			return listComputer;
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public Computer findById(Long id) {
		try {
			Statement stmt = conn.createStatement();
			String query = "SELECT * FROM computer WHERE computer.id = " + id;
			ResultSet result = stmt.executeQuery(query);
			DAOMapper<Computer> mapper = new ComputerMapper();
			Computer computer = mapper.find(result);
			result.close();
			stmt.close();
			return computer;
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Computer create(Computer obj) {
		try {
			String query = "INSERT INTO computer "
					+ "(name, introduced, discontinued, company_id) VALUES (?, "
					+ obj.getIntroduced() + ", "
					+ obj.getDiscontinued() + ", "
					+ obj.getCompany_id() + ")";
			PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, obj.getName());
			stmt.executeUpdate();
			ResultSet result = stmt.getGeneratedKeys();
			result.next();
			obj.setId((long) result.getInt(1));
			stmt.close();
			return obj;
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public boolean update(Computer obj) {
		try {
			String query = "UPDATE computer SET "
					+ "name = ?, "
					+ "introduced = " + obj.getIntroduced() + ", "
					+ "discontinued = " + obj.getDiscontinued() + ", "
					+ "company_id = " + obj.getCompany_id() + " "
					+ "WHERE id = " + obj.getId();
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setString(1, obj.getName());
			stmt.executeUpdate();
			stmt.close();
			return true;
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean delete(Computer obj) {
		try {
			Statement stmt = conn.createStatement();
			String query = "DELETE FROM computer WHERE id = " + obj.getId();
			stmt.executeUpdate(query);
			stmt.close();
			return true;
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
}
