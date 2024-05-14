package com.test.spring.mvc.util;

import com.test.spring.mvc.models.Person;
import com.test.spring.mvc.services.PeopleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
@Component
public class PersonValidator implements Validator {

    private final PeopleService peopleService;

    @Autowired
    public PersonValidator(PeopleService peopleService1) {
        this.peopleService = peopleService1;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Person.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Person person = (Person) target;

        if (peopleService.findPersonByEmail(person.getEmail()) != null) {
            errors.rejectValue("email", "", "This email is already taken");
        }

    }
}
