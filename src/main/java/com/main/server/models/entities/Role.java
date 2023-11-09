package com.main.server.models.entities;

import jakarta.persistence.*;
import lombok.Data;
import com.main.server.models.enumerations.ERole;
@Data
@Entity
@Table
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    @Enumerated(EnumType.STRING)
    private ERole name;
}
