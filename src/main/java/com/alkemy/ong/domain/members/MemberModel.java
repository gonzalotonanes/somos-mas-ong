package com.alkemy.ong.domain.members;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MemberModel {

    private Long id;
    private String name;
    private String facebookUrl;
    private String instagramUrl;
    private String linkedinUrl;
    private String image;
    private String description;
    private boolean deleted = Boolean.FALSE;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}