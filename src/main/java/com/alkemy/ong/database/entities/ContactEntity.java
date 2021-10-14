package com.alkemy.ong.database.entities;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Data;
import org.hibernate.annotations.*;

@Entity
@Table(name = "contacts")
@Data
@SQLDelete(sql = "UPDATE contacts SET deleted = true WHERE id=?")
@FilterDef(name = "deletedContactFilter", parameters = @ParamDef(name = "isDeleted", type = "boolean"))
@Filter(name = "deletedContactFilter", condition = "deleted = :isDeleted")
public class ContactEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Name field is required")
    @Column(length = 50, nullable = false)
    private String name;

    @NotEmpty(message = "Phone field is required")
    @Column(length = 50, nullable = false)
    private String phone;

    @NotEmpty(message = "Email field is required")
    @Column(length = 50, nullable = false)
    private String email;

    @NotEmpty(message = "Message field is required")
    @Column(length = 50, nullable = false)
    private String message;

    @Column(nullable = false)
    private boolean deleted = Boolean.FALSE;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime updatedAt;
}