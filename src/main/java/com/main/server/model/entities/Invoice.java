package com.main.server.model.entities;

import jakarta.persistence.*;
import lombok.Data;

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

    @ManyToOne
    private Product product;

    @ManyToOne
    private User user;
}
