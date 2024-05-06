package com.test.spring.mvc.springcourse.util;

import com.test.spring.mvc.springcourse.dao.PersonDao;
import com.test.spring.mvc.springcourse.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
@Component
public class PersonValidator implements Validator {

    private final PersonDao personDao;

    @Autowired
    public PersonValidator(PersonDao personDao) {
        this.personDao = personDao;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Person.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Person person = (Person) target;

        if (personDao.getPersonByEmail(person.getEmail()).isPresent()) {
            errors.rejectValue("email", "", "This email is already taken");
        }

    }
}
