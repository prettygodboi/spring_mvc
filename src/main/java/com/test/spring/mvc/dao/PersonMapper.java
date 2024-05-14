package com.test.spring.mvc.dao;

import com.test.spring.mvc.models.Person;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PersonMapper implements RowMapper<Person> {
    @Override
    public Person mapRow(ResultSet rs, int rowNum) throws SQLException {
        Person person = new Person();
        person.setId(rs.getLong("id"));
        person.setName(rs.getString("name"));
        person.setAge(rs.getInt("age"));
        person.setEmail(rs.getString("email"));
        return person;
    }
}
