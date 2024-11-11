package edu.upc.dsa.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.joda.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.joda.ser.LocalDateSerializer;
import edu.upc.dsa.util.RandomUtils;

import java.time.LocalDate;

public class User {
    private String id;
    private String name;
    private String surname;
    private String email;
    private String birthDate;

    public User() {
        this.id = RandomUtils.getId();
    }

    public User(String name, String surname, String email, String birthDate) {
        this.id = RandomUtils.getId();
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.birthDate = birthDate;
    }

    public User(String id, String name, String surname, String email, String birthDate) {
        if (id != null) this.setId(id);
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.birthDate = birthDate;
    }

    // Getters and Setters
    public String getId() { return id; }
    public void setId(String id) {
        this.id = (id == null || id.trim().isEmpty()) ? RandomUtils.getId() : id;
    }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getSurname() { return surname; }
    public void setSurname(String surname) { this.surname = surname; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getBirthDate() { return birthDate; }
    public void setBirthDate(String birthDate) { this.birthDate = birthDate; }

    @Override
    public String toString() {
        return "User [id=" + id + ", name=" + name + ", surname=" + surname +
                ", email=" + email + ", birthDate=" + birthDate + "]";
    }
}
