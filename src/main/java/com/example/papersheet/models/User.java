package com.example.papersheet.models;

import jakarta.persistence.*;


// @Entity marks this class as a JPA entity
@Entity
@Table(name = "users")
public class User {

    // @Id marks this field as the primary key
    // @GeneratedValue specifies the generation strategy for the primary key
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}