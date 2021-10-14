package com.alkemy.ong.domain.slides;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class SlideModel {
    private int id;
    private int idOrganization;
    private int order;
    private String text;
    private String imageUrl;
    private boolean deleted;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
