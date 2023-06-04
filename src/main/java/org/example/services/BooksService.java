package org.example.services;

import jakarta.persistence.criteria.CriteriaBuilder;
import org.example.models.Book;
import org.example.models.Person;
import org.example.repositories.BooksRepository;
import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional(readOnly = true)
public class BooksService {
    private final BooksRepository booksRepository;

    @Autowired
    public BooksService(BooksRepository booksRepository) {
        this.booksRepository = booksRepository;
    }

    public List<Book> findAll(boolean sort) {
        if (sort) {
            return booksRepository.findAll(Sort.by("year"));
        } else {
            return booksRepository.findAll();
        }
    }

    public Book findById(int id) {
        return booksRepository.findById(id).orElse(null);
    }

    @Transactional
    public void update(int id, Book book) {
        book.setBook_id(id);
        booksRepository.save(book);
    }

    @Transactional
    public void save(Book book) {
        booksRepository.save(book);
    }

    @Transactional
    public void deleteById(int id) {
        booksRepository.deleteById(id);
    }

    public Set<Book> findByOwner(Person person) {
        return new HashSet<>(booksRepository.findBooksByOwner(person));
    }

    public Optional<Book> findBookByNameAndAuthor(String name, String author) {
        return booksRepository.findBookByNameAndAuthor(name, author);
    }

    public List<Book> findAllWithPagination(Integer page, Integer booksPerPage, boolean sortByYear) {
        if (sortByYear)
            return booksRepository.findAll(PageRequest.of(page, booksPerPage, Sort.by("year"))).getContent();
        else
            return booksRepository.findAll(PageRequest.of(page, booksPerPage)).getContent();
    }

    public List<Book> findBooksByNameStartingWith(String starts) {
        return booksRepository.findBooksByNameStartingWith(starts);
    }
}
