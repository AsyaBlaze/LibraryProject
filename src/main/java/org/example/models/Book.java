package org.example.models;

import jakarta.validation.constraints.*;

public class Book {
    private int book_id;

    @NotEmpty(message = "The title of the book should not be empty")
    @Size(min = 2, max = 100, message = "The title of the book should be from 2 to 100 characters long")
    private String name;

    @NotEmpty(message = "The author should not be empty")
    @Size(min = 2, max = 100, message = "The author's name must be from 2 to 100 characters long")
    private String author;

    @NotNull
    private int year;

    public Book(int book_id, String name, String author, int year) {
        this.book_id = book_id;
        this.name = name;
        this.author = author;
        this.year = year;
    }

    public Book() {
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
}

