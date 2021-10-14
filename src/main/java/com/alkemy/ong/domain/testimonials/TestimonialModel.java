package com.alkemy.ong.domain.testimonials;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TestimonialModel {
    private Long id;
    private String name;
    private String image;
    private String content;
    private int deleted;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
