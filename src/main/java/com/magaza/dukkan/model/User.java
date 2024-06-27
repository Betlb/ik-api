package com.magaza.dukkan.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name= "user",schema = "public")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
    @SequenceGenerator(name = "user_seq", sequenceName = "user_seq", allocationSize = 1)
    private Long id;
    @Column(name ="username")
    private String username;

    @Column(name ="password")
    private String password;

    @Override
    public String toString() {
        return "{" +
                " \"username\":\"" + username + "\"," +
                '}';
    }
}

