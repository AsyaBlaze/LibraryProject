package org.example.dao;

import org.example.models.Book;
import org.example.models.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Component
public class BookDAO {
//    private final SessionFactory sessionFactory;
//
//    @Autowired
//    public BookDAO(SessionFactory sessionFactory) {
//        this.sessionFactory = sessionFactory;
//    }
//
//    @Transactional
//    public List<Book> index() {
//        Session session = sessionFactory.getCurrentSession();
//        return session.createQuery("select b from Book b", Book.class).getResultList();
//    }
//
//    @Transactional
//    public Optional<Book> show(String name, String author) {
//        Session session = sessionFactory.getCurrentSession();
//        return session.createQuery("select b from Book b where b.name=:name_t and b.author=:author_t", Book.class)
//                .setParameter("name_t", name).setParameter("author_t", author).stream().findAny();
//    }
//
//    @Transactional
//    public Book findById(int id) {
//        Session session = sessionFactory.getCurrentSession();
//        return session.get(Book.class, id);
//    }
//
//    @Transactional
//    public List<Book> findByOwner(int id) {
//        Session session = sessionFactory.getCurrentSession();
//        return session.createQuery("SELECT b from Book b where b.owner.person_id=:id", Book.class)
//                .setParameter("id", id).getResultList();
//    }
//
//    @Transactional
//    public void setOwner(int id_p, int id_b) {
//        Session session = sessionFactory.getCurrentSession();
//        Book book = session.get(Book.class, id_b);
//        Person person = session.get(Person.class, id_p);
//        book.setOwner(person);
//        person.setBook(book);
//    }
//
//    @Transactional
//    public void deleteOwner(int id_b) {
//        Session session = sessionFactory.getCurrentSession();
//        Book book = session.get(Book.class, id_b);
//        Person person = book.getOwner();
//        book.setOwner(null);
//        person.getBooks().remove(book);
//    }
//
//    @Transactional
//    public String getOwner(int id_b) {
//        Session session = sessionFactory.getCurrentSession();
//        Book book = session.get(Book.class, id_b);
//        return book.getOwner() == null ? null : book.getOwner().getFull_name();
//    }
//
//    @Transactional
//    public void save(Book book) {
//        Session session = sessionFactory.getCurrentSession();
//        session.persist(book);
//    }
//
//    @Transactional
//    public void delete(int id) {
//        Session session = sessionFactory.getCurrentSession();
//        Book book = session.get(Book.class, id);
//        session.remove(book);
//    }
//
//    @Transactional
//    public void update(int id, Book book) {
//        Session session = sessionFactory.getCurrentSession();
//        Book curBook = session.get(Book.class, id);
//        curBook.setName(book.getName());
//        curBook.setAuthor(book.getAuthor());
//        curBook.setYear(book.getYear());
//    }
}
