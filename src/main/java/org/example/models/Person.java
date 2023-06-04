package org.example.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Entity
@Table(name = "Person")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "person_id")
    private int person_id;

    @NotEmpty(message = "The name should not be empty")
    @Size(min = 2, max = 100, message = "The name must be between 2 and 100 characters long")
    @Column(name = "full_name")
    private String fullName;

    @NotNull(message = "The year of birth must be filled in")
    @Column(name = "year_birthday")
    private int year_birthday;

    @OneToMany(mappedBy = "owner")
    private List<Book> books;

    public Person() {
    }

    public Person(String fullName, int year_birthday) {
        this.fullName = fullName;
        this.year_birthday = year_birthday;
    }

    public int getYear_birthday() {
        return year_birthday;
    }

    public void setYear_birthday(int year_birthday) {
        this.year_birthday = year_birthday;
    }

    public int getPerson_id() {
        return person_id;
    }

    public void setPerson_id(int id) {
        this.person_id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String full_name) {
        this.fullName = full_name;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBook(Book book) {
        if (books.isEmpty()) {
            books = new ArrayList<>();
        }
        books.add(book);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return person_id == person.person_id && year_birthday == person.year_birthday && Objects.equals(fullName, person.fullName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(person_id, fullName, year_birthday);
    }
}
