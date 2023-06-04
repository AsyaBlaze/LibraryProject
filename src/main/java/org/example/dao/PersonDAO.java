package org.example.dao;

import org.example.models.Book;
import org.example.models.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Component
public class PersonDAO {
//    private final SessionFactory sessionFactory;
//
//    @Autowired
//    public PersonDAO(SessionFactory sessionFactory) {
//        this.sessionFactory = sessionFactory;
//    }
//
//    @Transactional
//    public List<Person> index() {
//        Session session = sessionFactory.getCurrentSession();
//        return session.createQuery("SELECT p from Person p", Person.class).getResultList();
//    }
//
//    @Transactional
//    public Optional<Person> show(String full_name) {
//        Session session = sessionFactory.getCurrentSession();
//        return session.createQuery("select p from Person p where p.full_name=:name", Person.class)
//                .setParameter("name", full_name).stream().findAny();
//    }
//
//    @Transactional
//    public Person findById(int id) {
//        Session session = sessionFactory.getCurrentSession();
//        return session.get(Person.class, id);
//    }
//
//    @Transactional
//    public void save(Person person) {
//        Session session = sessionFactory.getCurrentSession();
//        session.persist(person);
//    }
//
//    @Transactional
//    public void update(int id, Person person) {
//        Session session = sessionFactory.getCurrentSession();
//        Person curPerson = session.get(Person.class, id);
//        curPerson.setFull_name(person.getFull_name());
//        curPerson.setYear_birthday(person.getYear_birthday());
//    }
//
//    @Transactional
//    public void delete(int id) {
//        Session session = sessionFactory.getCurrentSession();
//        Person person = session.get(Person.class, id);
//        session.remove(person);
//    }

}

