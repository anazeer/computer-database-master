package com.excilys.cdb.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
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
		List<Computer> listComputer = null;
		try {
			Statement stmt = conn.createStatement();
			String query = "SELECT * FROM computer";
			ResultSet result = stmt.executeQuery(query);
			DAOMapper<Computer> mapper = new ComputerMapper();
			listComputer = new ArrayList<Computer>();
			while(result.next())
				listComputer.add(mapper.find(result));
			result.close();
			stmt.close();
			return listComputer;
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return listComputer;
	}
	
	@Override
	public Computer findById(Long id) {
		Computer computer = null;
		try {
			Statement stmt = conn.createStatement();
			String query = "SELECT * FROM computer WHERE computer.id = " + id;
			ResultSet result = stmt.executeQuery(query);
			DAOMapper<Computer> mapper = new ComputerMapper();
			if(result.next()) {
				computer = mapper.find(result);
			}
			result.close();
			stmt.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return computer;
	}

	@Override
	public Computer create(Computer obj) {
		try {
			String query = "INSERT INTO computer "
					+ "(name, introduced, discontinued, company_id) VALUES (?, ?, ?, "
					+ obj.getCompany_id() + ")";
			PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, obj.getName());
			stmt.setTimestamp(2, new Timestamp(obj.getIntroduced().getTime()));
			Timestamp discontinued = obj.getDiscontinued() == null ? null : new Timestamp(obj.getDiscontinued().getTime());
			stmt.setTimestamp(3, discontinued);
			stmt.executeUpdate();
			ResultSet result = stmt.getGeneratedKeys();
			result.next();
			obj.setId((long) result.getInt(1));
			stmt.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return obj;
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
	
	public boolean delete(Long id) {
		try {
			Statement stmt = conn.createStatement();
			String query = "DELETE FROM computer WHERE id = " + id;
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
