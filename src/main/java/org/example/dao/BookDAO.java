package org.example.dao;

import org.example.models.Book;
import org.example.models.Person;
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
        return jdbcTemplate.query("SELECT * FROM Book WHERE book_id = ?", new Object[]{id},
                new BeanPropertyRowMapper<>(Book.class)).stream().findAny().orElse(null);
    }

    public List<Book> findByOwner(int id) {
        return jdbcTemplate.query("SELECT * FROM Book WHERE id_person = ?", new Object[]{id},
                new BeanPropertyRowMapper<>(Book.class));
    }

    public void setOwner(int id_p, int id_b) {
        jdbcTemplate.update("UPDATE Book set id_person=? where book_id=?", id_p, id_b);
    }

    public void deleteOwner(int id_b) {
        jdbcTemplate.update("UPDATE Book set id_person=null where book_id=?", id_b);
    }

    public String getOwner(int id_b) {
        List<Person> list = jdbcTemplate.query("SELECT Person.* FROM Book JOIN Person ON Book.id_person = person.person_id WHERE Book.book_id = ?",
                new Object[]{id_b}, new BeanPropertyRowMapper<>(Person.class));
        return list.size() == 0 ? null : list.get(0).getFull_name();
    }

    public void save(Book book) {
        System.out.println(book.getName() + " " + book.getAuthor() + " " + book.getYear());
        jdbcTemplate.update("INSERT INTO Book(name, author, year) VALUES (?, ?, ?)", book.getName(),
                book.getAuthor(), book.getYear());
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM Book WHERE book_id=?", id);
    }

    public void update(int id, Book book) {
        jdbcTemplate.update("UPDATE Book set name=?, author=?, year=? where book_id = ?", book.getName(), book.getAuthor(),
                book.getYear(), id);
    }
}
