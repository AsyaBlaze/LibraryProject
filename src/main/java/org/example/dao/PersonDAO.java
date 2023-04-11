package org.example.dao;

import org.example.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class PersonDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> index() {
        return jdbcTemplate.query("SELECT * FROM Person", new BeanPropertyRowMapper<>(Person.class));
    }

    public Optional<Person> show(String full_name) {
        return jdbcTemplate.query("SELECT * FROM Person WHERE full_name=?", new Object[]{full_name},
                new BeanPropertyRowMapper<>(Person.class)).stream().findAny();
    }

    public Person findById(int id) {
        return jdbcTemplate.query("SELECT * FROM Person WHERE person_id = ?", new Object[] {id},
                new BeanPropertyRowMapper<>(Person.class)).stream().findAny().orElse(null);
    }

    public void save(Person person) {
        jdbcTemplate.update("INSERT INTO Person(full_name, year_birthday) VALUES (?, ?)", person.getFull_name(),
                person.getYear_birthday());
    }

    public void update(int id, Person person) {
        jdbcTemplate.update("UPDATE Person set full_name=?, year_birthday=? where person_id = ?", person.getFull_name(),
                person.getYear_birthday(), id);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM Person WHERE person_id=?", id);
    }

}

