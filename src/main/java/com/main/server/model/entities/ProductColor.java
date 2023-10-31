package com.main.server.model.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table
public class ProductColor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String name;

    @Column
    private  String src;

    @Column(columnDefinition = "double default 0.0")
    private double extraCoefficient = 0.0;

    @ManyToOne
    private Product product;
}

