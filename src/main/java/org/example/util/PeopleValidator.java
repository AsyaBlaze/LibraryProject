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
            errors.rejectValue("full_name", "", "Это ФИО уже есть в Базе данных");
        }
        if (Integer.parseInt(person.getYearOfBirthday()) > (Year.now().getValue() - 14)) {
            errors.rejectValue("birthday", "", "Боюсь вы слишком молоды чтобы брать книги");
        }
        if (Integer.parseInt(person.getYearOfBirthday()) < (Year.now().getValue() - 130)) {
            errors.rejectValue("birthday", "", "Боюсь это не библиотека для сверхестественных " +
                    "существ. Либо вы ввели неправильный год рождения");
        }
    }
}

