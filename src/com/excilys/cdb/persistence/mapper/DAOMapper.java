package com.excilys.cdb.persistence.mapper;

import java.sql.ResultSet;
import java.util.List;

/**
 * 
 * @author excilys
 *
 * @param <T>
 */
public interface DAOMapper<T> {

	public T find(ResultSet result);
	public List<T> findAll(ResultSet result);

}
