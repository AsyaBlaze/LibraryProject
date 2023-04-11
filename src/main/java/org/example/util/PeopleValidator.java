package org.example.util;

import org.example.dao.PersonDAO;
import org.example.models.Person;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.time.Year;

@Component
public class PeopleValidator implements Validator {

    private final PersonDAO personDAO;

    public PeopleValidator(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Person.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Person person = (Person) target;

        if (personDAO.show(person.getFull_name()).isPresent()) {
            errors.rejectValue("full_name", "", "This full name is already in the Database");
        }
        if (person.getYear_birthday() > (Year.now().getValue() - 14)) {
            errors.rejectValue("birthday", "", "I'm afraid you're too young to take books");
        }
        if (person.getYear_birthday() < (Year.now().getValue() - 130)) {
            errors.rejectValue("birthday", "", "I'm afraid this is not a library for supernatural beings. " +
                    "Or you entered the wrong year of birth");
        }
    }
}

