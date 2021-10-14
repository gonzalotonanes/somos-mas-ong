package com.alkemy.ong.web.dto;

import lombok.Data;

import java.util.List;

@Data
public class PageDto {
    private List<?> content;
    private String previousPage;
    private String nextPage;
}
