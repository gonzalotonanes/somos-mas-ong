package com.alkemy.ong.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class NewsDto{

    private Long id;

    //@NotEmpty(message = "idCategory field is required")
    private int idCategory;

    @NotEmpty(message = "Name field is required")
    private String name;

    @NotEmpty(message = "Image field is required")
    private String image;

    @NotEmpty(message = "Content field is required")
    private String content;

    private boolean deleted;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public NewsDto(Long id, int idCategory, String name, String content, String image, LocalDateTime createdAt, LocalDateTime updatedAt) {
    }
}
