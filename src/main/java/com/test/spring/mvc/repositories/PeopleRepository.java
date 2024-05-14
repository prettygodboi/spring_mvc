package com.test.spring.mvc.repositories;
import com.test.spring.mvc.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PeopleRepository extends JpaRepository<Person, Long> {
    Person findPersonByEmail(String email);
}
