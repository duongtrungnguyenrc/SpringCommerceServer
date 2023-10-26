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

    @Column
    private double extraCoefficient;

    @ManyToOne
    private Product product;
}

