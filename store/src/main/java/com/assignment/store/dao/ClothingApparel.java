package com.assignment.store.dao;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Table(name = "CLOTHING")
@Entity
public class ClothingApparel extends Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CLOTHING_SEQ")
    @SequenceGenerator(name = "CLOTHING_SEQ", sequenceName = "CLOTHING_SEQ", allocationSize = 1)
    protected Long id;

    @Column(name = "COLOR")
    private String color;

    @Column(name = "EU_SIZE")
    private String euSize;

}
