package com.main.server.models.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Collection;
import java.util.Date;

@Data
@Entity
@Table
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private Date time;

    @Column
    private Double sumPrice;

    @ManyToMany(mappedBy = "invoices")
    private Collection<Product> products;

    @ManyToOne
    private User user;
}
