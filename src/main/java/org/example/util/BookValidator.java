package org.example.util;

import org.example.dao.BookDAO;
import org.example.models.Book;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.time.Year;

@Component
public class BookValidator implements Validator {

    private final BookDAO bookDAO;

    public BookValidator(BookDAO bookDAO) {
        this.bookDAO = bookDAO;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Book.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Book book = (Book) target;

        if (bookDAO.show(book.getName(), book.getAuthor()).isPresent()) {
            errors.rejectValue("name", "", "Книга с таким названием и автором уже есть в Базе Данных");
            errors.rejectValue("author", "", "Книга с таким названием и автором уже есть в Базе Данных");
        }
        String year = String.valueOf(book.getYear());
        if (year.length() != 4) {
            errors.rejectValue("year", "", "Год должен состоять из 4 цифр");
        }
        if (book.getYear() > (Year.now().getValue())) {
            errors.rejectValue("year", "", "Вы не можете добавить книгу, так как она ещё не вышла");
        }
    }
}
