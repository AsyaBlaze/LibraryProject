package org.example.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.Duration;
import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.TemporalAmount;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Objects;

@Entity
@Table(name = "Book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private int book_id;

    @NotEmpty(message = "The title of the book should not be empty")
    @Size(min = 2, max = 100, message = "The title of the book should be from 2 to 100 characters long")
    @Column(name = "name")
    private String name;

    @NotEmpty(message = "The author should not be empty")
    @Size(min = 2, max = 100, message = "The author's name must be from 2 to 100 characters long")
    @Column(name = "author")
    private String author;

    @NotNull
    @Column(name = "year")
    private int year;

    @Column(name = "date_issue")
    @Temporal(TemporalType.DATE)
    private Date dateIssue;

    @Transient
    private boolean isOverdue;

    @ManyToOne
    @JoinColumn(name = "id_person", referencedColumnName = "person_id")
    private Person owner;

    public Book(String name, String author, int year) {
        this.name = name;
        this.author = author;
        this.year = year;
        this.dateIssue = null;
    }

    public Book() {
    }

    public void setOverdue() {
        this.isOverdue = this.isOverdue();
    }

    public boolean getOverdue() {
        return this.isOverdue;
    }

    public Date getDateIssue() {
        return dateIssue;
    }

    public void setDateIssue(Date dateIssue) {
        this.dateIssue = dateIssue;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    public Person getOwner() {
        return owner;
    }

    public void setOwner(Person owner) {
        this.owner = owner;
        this.dateIssue = new Date();
    }


    public boolean isOverdue() {
        Date dateIssue = this.dateIssue;
        if (dateIssue == null) {
            return false;
        }
        long days = new Date().getTime() - dateIssue.getTime();
        return days > 864000000;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return book_id == book.book_id && year == book.year && Objects.equals(name, book.name) && Objects.equals(author, book.author);
    }

    @Override
    public int hashCode() {
        return Objects.hash(book_id, name, author, year);
    }
}

