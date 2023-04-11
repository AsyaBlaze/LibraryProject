package org.example.models;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class Person {

    @NotEmpty(message = "The name should not be empty")
    @Size(min = 2, max = 100, message = "The name must be between 2 and 100 characters long")
    private String full_name;

    @NotNull(message = "The year of birth must be filled in")
    private int year_birthday;

    private int person_id;

    public Person() {
    }

    public Person(int id, String full_name, int year_birthday) {
        this.full_name = full_name;
        this.year_birthday = year_birthday;
        this.person_id = id;
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

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }
}
