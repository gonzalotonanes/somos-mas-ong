package com.alkemy.ong.database.entities;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Setter
@Getter
@Entity
@Table(name = "Slide")
public class SlideEntity implements Serializable {
    @Id
    @Column(name = "idSlide")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    @Column(name = "idOrganization")
    private int idOrganization;

    @Column(name = "ordinalsNumber")
    private int ordinalNumber;

    @Column(name = "text")
    private String text;

    @Column(name = "imageUrl")
    private String imageUrl;

    @Column(nullable = false)
    private boolean deleted = Boolean.FALSE;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime updatedAt;
}
