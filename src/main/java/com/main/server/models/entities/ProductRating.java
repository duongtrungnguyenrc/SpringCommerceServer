package com.main.server.models.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table
public class ProductRating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String comment;

    @Column
    private int star;

    @Column
    private Double time;

    @ManyToOne
    private Product product;

    @ManyToOne
    private User user;
}
