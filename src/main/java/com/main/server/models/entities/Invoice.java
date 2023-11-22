package com.main.server.models.entities;

import com.main.server.models.enumerations.EOrderStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.Date;

@Data
@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private Date time;

    @Column
    @Enumerated(EnumType.STRING)
    private EOrderStatus status;

    @ManyToMany
    @JoinTable(
            name = "productInvoice",
            joinColumns = @JoinColumn(name = "invoiceId"),
            inverseJoinColumns = @JoinColumn(name = "productId")
    )
    private Collection<Product> products;

    @ManyToOne
    private User user;
}
