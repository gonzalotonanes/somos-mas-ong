package com.alkemy.ong.database.entities;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="SAMPLE")
public class SampleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    private String name;

    private String description;
}
