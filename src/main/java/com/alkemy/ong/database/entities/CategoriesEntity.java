package com.alkemy.ong.database.entities;

import lombok.Data;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Table(name = "categories")
@SQLDelete(sql = "UPDATE categories SET deleted = true WHERE id=?")
@Where(clause = "deleted=false")
public class CategoriesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Name field is required")
    @Column(name = "name", length = 45)
    private String name;

    @NotEmpty(message = "description field is required")
    @Column(name = "description")
    private String description;

    @NotEmpty(message = "image field is required")
    @Column(name = "image", length = 45)
    private String image;

    @Temporal(TemporalType.TIMESTAMP)
    @Generated(value = GenerationTime.INSERT)
    @Column(name="created_at", updatable = false, nullable = false, insertable = false,
        columnDefinition = "datetime default CURRENT_TIMESTAMP")
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Generated(value = GenerationTime.ALWAYS)
    @Column(name = "updated_at", updatable = false, nullable = false, insertable = false,
            columnDefinition = "datetime default CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private Date updatedAt;

    @Column(name = "deleted", nullable = false)
    private boolean deleted = Boolean.FALSE;
}