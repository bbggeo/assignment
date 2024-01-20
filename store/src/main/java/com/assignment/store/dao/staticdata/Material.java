package com.assignment.store.dao.staticdata;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Table(name = "MATERIAL")
@Entity
public class Material {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;

    @Column(name = "NAME")
    protected String name;

    @Column(name = "DESCRIPTION")
    protected String description;

}
