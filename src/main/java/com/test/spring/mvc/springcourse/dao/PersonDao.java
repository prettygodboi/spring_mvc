package com.test.spring.mvc.springcourse.dao;

import com.test.spring.mvc.springcourse.models.Person;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class PersonDao {
    private List<Person> people;
    private static int COUNT_PEOPLE;

    {
        people = new ArrayList<>();
        people.add(new Person(++COUNT_PEOPLE,"Dima", 18, "ddsa.sda"));
        people.add(new Person(++COUNT_PEOPLE,"Vas",123,"asad.dss"));
        people.add(new Person(++COUNT_PEOPLE,"Bob", 21, "sadas.sda"));
        people.add(new Person(++COUNT_PEOPLE,"Mike", 562,"sad.ccc"));
    }

    public List<Person> getPeople(){
        return people;
    }

    public Person getPersonById(int id){
        return people.stream().filter(person -> person.getId() == id).findFirst().orElse(null);
    }

    public void createNewPerson(Person person) {
        person.setId(++COUNT_PEOPLE);
        people.add(person);
    }

    public void editPerson(int id, Person person) {
        Person updatedPerson = getPersonById(id);
        updatedPerson.setName(person.getName());
        updatedPerson.setAge(person.getAge());
        updatedPerson.setEmail(person.getEmail());
    }

    public void deletePerson(int id) {
        people.removeIf(person -> person.getId() == id);
    }
}
