package com.test.spring.mvc.springcourse.dao;

import com.test.spring.mvc.springcourse.models.Person;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class PersonDao {
    private List<Person> people;

    {
        people = new ArrayList<>();
        people.add(new Person(1,"Dima"));
        people.add(new Person(2,"Vas"));
        people.add(new Person(3,"Bob"));
        people.add(new Person(4,"Mike"));
    }

    public List<Person> getPeople(){
        return people;
    }

    public Person getPersonById(int id){
        return people.stream().filter(person -> person.getId() == id).findFirst().orElse(null);
    }
}
