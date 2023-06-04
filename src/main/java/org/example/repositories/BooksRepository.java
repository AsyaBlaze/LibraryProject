package org.example.repositories;

import org.example.models.Book;
import org.example.models.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BooksRepository extends JpaRepository<Book, Integer> {
    List<Book> findBooksByOwner(Person person);

    Optional<Book> findBookByNameAndAuthor(String name, String author);

    List<Book> findBooksByNameStartingWith(String starts);
}
