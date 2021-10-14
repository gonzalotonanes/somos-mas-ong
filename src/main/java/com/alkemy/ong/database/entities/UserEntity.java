package com.alkemy.ong.database.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long idUser;

    @Column(name = "firstName", length = 30, nullable = false)
    private String firstName;

    @Column(name = "lastName", length = 30, nullable = false)
    private String lastName;

    @Column(name = "email", length = 40, nullable = false, unique = true)
    private String email;

    @Column(name = "password", length = 100, nullable = false)
    private String password;

    @Column(name = "photo", length = 30, nullable = true)
    private String photo;

    @Column(name = "deleted", nullable = false)
    private boolean deleted;

    @Column(name= "createdAt", nullable = false)
    private LocalDateTime createdAt;

    @Column(name= "updatedAt", nullable = false)
    private LocalDateTime updatedAt;
    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_role")
    private RoleEntity roleId;

    public UserEntity(){
        super();
        this.deleted = false;
    }
}