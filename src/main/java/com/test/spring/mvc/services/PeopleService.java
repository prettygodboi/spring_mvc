package com.test.spring.mvc.services;

import com.test.spring.mvc.models.Book;
import com.test.spring.mvc.models.Person;
import com.test.spring.mvc.repositories.PeopleRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PeopleService {
    private final PeopleRepository peopleRepository;


    public List<Person> findAll() {
        return peopleRepository.findAll();
    }

    public Person findOne(Long id) {
        Optional<Person> person = peopleRepository.findById(id);
        return person.orElse(null);
    }

    public Person findPersonByEmail(String email) {
        return peopleRepository.findPersonByEmail(email);
    }

    public List<Book> findBooksByPersonId(Long id) {
        Optional<Person> person = peopleRepository.findById(id);
        if (person.isPresent()) {
            Hibernate.initialize(person.get().getBooks());
            return person.get().getBooks();
        } else {
            return Collections.emptyList();
        }
    }

    public void save(Person person) {
        peopleRepository.save(person);
    }

    public void update(Long id, Person updatedPerson) {
        updatedPerson.setId(id);
        peopleRepository.save(updatedPerson);
    }

    public void remove(Long id) {
        peopleRepository.deleteById(id);
    }
}
