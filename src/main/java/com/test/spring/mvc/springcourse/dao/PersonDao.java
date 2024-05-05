package com.test.spring.mvc.springcourse.dao;

import com.test.spring.mvc.springcourse.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PersonDao {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> getPeople() {
        return jdbcTemplate.query("SELECT * FROM person", new PersonMapper());
    }

    public Person getPersonById(int id) {

        return jdbcTemplate.query("SELECT * FROM person WHERE id=?", new BeanPropertyRowMapper<>(Person.class), new Object[]{id})
                .stream().findAny().orElse(null);
    }

    public void createNewPerson(Person person) {
        jdbcTemplate.update("INSERT INTO person(name, age, email) VALUES(?,?,?)", person.getName(), person.getAge(), person.getEmail());
    }

    public void editPerson(int id, Person updatedPerson) {
        jdbcTemplate.update("UPDATE person SET name=?, age=?, email=? WHERE id=?", updatedPerson.getName(), updatedPerson.getAge(), updatedPerson.getEmail(), id);

    }

    public void deletePerson(int id) {
        jdbcTemplate.update("DELETE FROM person WHERE id=?", id);
    }
}
