package com.test.spring.mvc.springcourse.dao;

import com.test.spring.mvc.springcourse.models.Person;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class PersonDao {
    private static int COUNT_PEOPLE;
    private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "152630";

    private static Connection connection;

    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Person> getPeople() {
        List<Person> people = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            String SQL = "SELECT * FROM person";
            ResultSet resultSet = statement.executeQuery(SQL);
            while (resultSet.next()) {
                Person person = new Person();
                person.setId(resultSet.getInt("id"));
                person.setName(resultSet.getString("name"));
                person.setAge(resultSet.getInt("age"));
                person.setEmail(resultSet.getString("email"));
                people.add(person);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return people;
    }

    public Person getPersonById(int id) {
//        return people.stream().filter(person -> person.getId() == id).findFirst().orElse(null);
        return null;
    }

    public void createNewPerson(Person person) {
//        person.setId(++COUNT_PEOPLE);
//        people.add(person);
        try {
            Statement statement = connection.createStatement();
            String SQL = "INSERT INTO person(name, age, email) VALUES('" + person.getName() + "'," + person.getAge() + ",'" + person.getEmail() + "')";
            statement.executeUpdate(SQL);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void editPerson(int id, Person person) {
        Person updatedPerson = getPersonById(id);
        updatedPerson.setName(person.getName());
        updatedPerson.setAge(person.getAge());
        updatedPerson.setEmail(person.getEmail());
    }

    public void deletePerson(int id) {
//        people.removeIf(person -> person.getId() == id);
    }
}
