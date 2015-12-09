package com.css.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.RowMapper;

import com.css.model.Person;

@Repository
public class PersonRepository {
	
	protected final Logger log = LoggerFactory.getLogger(getClass());
	
	  
	@Autowired
	@Qualifier("jdbcTest")
	protected JdbcTemplate jdbc;
	
	private String SQL_GET = "SELECT * FROM person WHERE id=?";
	private String SQL_GET_ALL = "SELECT * FROM person";
	private String SQL_CREATE = "INSERT INTO person(id, name) VALUES (?, ?)";
	
	private static final RowMapper<Person> personMapper = new RowMapper<Person>() {
		@Override
		public Person mapRow(ResultSet rs, int rowNum) throws SQLException {
        	Person p = new Person();
        	p.setId(rs.getLong("id")); 
        	p.setName(rs.getString("name"));
            return p;
        }
    };
    
	public Person getPerson(long id) {
		return jdbc.queryForObject(SQL_GET, personMapper, id);
	}
	
	public List<Person> getAllPersons() {
		return (List<Person>) jdbc.query(SQL_GET_ALL, personMapper);
	}
	
	public Person createPerson(long id, String name) {
		jdbc.update(SQL_CREATE, new Object[] {id, name});
		return jdbc.queryForObject(SQL_GET, personMapper, id);
	}	

}