package org.example.dao;

import org.example.models.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class BookDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> index() {
        return jdbcTemplate.query("SELECT * FROM Book", new BeanPropertyRowMapper<>(Book.class));
    }

    public Optional<Book> show(String name, String author) {
        return jdbcTemplate.query("SELECT * FROM Book WHERE name=? AND author=?", new Object[]{name, author},
                new BeanPropertyRowMapper<>(Book.class)).stream().findAny();
    }

    public Book findById(int id) {
        return jdbcTemplate.query("SELECT * FROM Book WHERE book_id = ?", new Object[] {id},
                new BeanPropertyRowMapper<>(Book.class)).stream().findAny().orElse(null);
    }

    public List<Book> findByOwner(int id) {
        return jdbcTemplate.query("SELECT * FROM Book WHERE person_id = ?", new Object[] {id},
                new BeanPropertyRowMapper<>(Book.class));
    }

    public void save(Book book) {
        System.out.println(book);
        jdbcTemplate.update("INSERT INTO Book(name, author, year) VALUES (?, ?, ?)", book.getName(),
                book.getAuthor(), book.getYear());
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM Book WHERE book_id=?", id);
    }
}
