package org.example.models;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class Person {
    @NotNull
    private String full_name;

    @NotNull
    @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = "Дата рождения должна быть в формате: 0000-00-00")
    private String birthday;

    private int person_id;

    public Person() {
    }

    public Person(int id, String full_name, String birthday) {
        this.full_name = full_name;
        this.birthday = birthday;
        this.person_id = id;
    }

    public String getBirthday() {
        return birthday;
    }

    public String getYearOfBirthday() {
        String[] date = birthday.split("-");
        return date[0];
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
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
