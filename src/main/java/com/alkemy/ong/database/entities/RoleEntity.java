package com.alkemy.ong.database.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "roles")
public class RoleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRole;

    @Column(name = "name", length = 15, nullable = false)
    private String name;

    @Column(name = "description", length = 30, nullable = true)
    private String description;

    @Column(name= "createdAt", nullable=false)
    private LocalDateTime createdAt;

    @Column(name= "updatedAt", nullable=false)
    private LocalDateTime updatedAt;
    @JsonManagedReference
    @OneToMany(mappedBy = "roleId", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<UserEntity> users;
}