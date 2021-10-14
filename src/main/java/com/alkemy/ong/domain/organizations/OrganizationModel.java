package com.alkemy.ong.domain.organizations;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrganizationModel {
    private int id;
    private String name;
    private String image;
    private String address;
    private Integer phone;
    private String email;
    private String welcomeText;
    private String aboutUsText;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private boolean deleted = false;
    private String urlFacebook;
    private String urlLinkedin;
    private String urlInstagram;
}
