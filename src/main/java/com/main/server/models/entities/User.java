package com.main.server.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.main.server.models.enumerations.EGender;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String name;

    @Column
    private Date birth;

    @Column
    @Enumerated(EnumType.STRING)
    private EGender gender;

    @Column(unique = true)
    private String phone;

    @Column
    private String address;

    @Column(unique = true)
    private String email;

    @Column
    @JsonIgnore
    private String password;

    @ManyToOne
    private Role role;

    public User(String name, Date birth, EGender gender, String phone, String address, String email, String password) {
        this.name = name;
        this.birth = birth;
        this.gender = gender;
        this.phone = phone;
        this.address = address;
        this.email = email;
        this.password = password;
    }
}
