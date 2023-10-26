package com.main.server.model.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;
import java.util.Set;

@Data
@Entity
@Table
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String name;

    @Column
    private Date birth;

    @Column
    private String gender;

    @Column(unique = true)
    private String phone;

    @Column
    private String address;

    @Column(unique = true)
    private String email;

    @Column
    private String password;

    @ManyToOne
    private Role role;
}
