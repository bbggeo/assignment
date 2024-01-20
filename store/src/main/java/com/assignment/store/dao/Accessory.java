package com.assignment.store.dao;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Table(name = "ACCESSORY")
@Entity
public class Accessory extends Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;
}
