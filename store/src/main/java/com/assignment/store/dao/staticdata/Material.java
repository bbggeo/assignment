package com.assignment.store.dao.staticdata;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Table(name = "MATERIAL")
@Entity
public class Material {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MATERIAL_SEQ")
    @SequenceGenerator(name = "MATERIAL_SEQ", sequenceName = "MATERIAL_SEQ", allocationSize = 1)
    protected Long id;

    @Column(name = "NAME")
    protected String name;

    @Column(name = "DESCRIPTION")
    protected String description;

}
