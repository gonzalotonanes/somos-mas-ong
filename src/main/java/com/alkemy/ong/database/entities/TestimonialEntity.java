package com.alkemy.ong.database.entities;

import lombok.Data;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "testimonials")
public class TestimonialEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    @Column (name = "name", length = 30, nullable = false)
    private String name;

    @Column (name = "image", length = 100)
    private String image;

    @Column (name = "content", length = 200)
    private String content;

    @Column (name = "deleted", length = 1)
    private int deleted;

    @Generated( value = GenerationTime.ALWAYS)
    @Column (name = "created_at", length = 30, nullable = false,
            columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdAt;

    @Generated( value = GenerationTime.ALWAYS)
    @Column (name = "updated_at", length = 30, nullable = false,
            columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private LocalDateTime updatedAt;

}
