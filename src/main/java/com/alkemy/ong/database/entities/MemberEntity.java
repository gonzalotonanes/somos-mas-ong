package com.alkemy.ong.database.entities;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name="members")
public class MemberEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    @NotEmpty(message = "Name field is required")
    @Column(nullable = false, length = 25)
    @NotNull
    private String name;

    @Column(length = 50, name = "facebook_url")
    private String facebookUrl;

    @Column(length = 50, name="instagram_url")
    private String instagramUrl;

    @Column(length = 50, name = "linkedin_url")
    private String linkedinUrl;

    @NotEmpty(message = "Image field is required")
    @Column(nullable = false)
    private String image;

    @Column(length = 50)
    private String description;

    @Column(nullable = false)
    private boolean deleted = Boolean.FALSE;

   @Column(name = "created_at", nullable = false,
           columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdAt;

   @Column(name = "updated_at", nullable = false,
           columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime updatedAt;
}
