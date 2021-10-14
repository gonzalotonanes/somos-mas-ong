package com.alkemy.ong.domain.news;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class NewsModel {

    private Long id;
    private int idCategory;
    private String name;
    private String image;
    private String content;
    private boolean deleted = Boolean.FALSE;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}


