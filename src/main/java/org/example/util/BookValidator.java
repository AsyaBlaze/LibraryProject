package org.example.util;

import org.example.models.Book;
import org.example.services.BooksService;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.time.Year;

@Component
public class BookValidator implements Validator {

    private final BooksService booksService;

    public BookValidator(BooksService booksService) {
        this.booksService = booksService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Book.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Book book = (Book) target;

        if (booksService.findBookByNameAndAuthor(book.getName(), book.getAuthor()).isPresent()) {
            errors.rejectValue("name", "", "A book with the same title and author already exists.");
            errors.rejectValue("author", "", "A book with the same title and author already exists.");
        }
        String year = String.valueOf(book.getYear());
        if (year.length() != 4) {
            errors.rejectValue("year", "", "Year must be 4 digits");
        }
        if (book.getYear() > (Year.now().getValue())) {
            errors.rejectValue("year", "", "You can't add the book because it hasn't been released yet");
        }
    }
}
