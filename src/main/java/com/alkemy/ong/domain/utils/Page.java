package com.alkemy.ong.domain.utils;

import lombok.Data;

import java.util.List;

@Data
public class Page<T> {
    private boolean hasNextPage;
    private boolean hasPreviousPage;
    private int currentPage;
    List<T> content;
}
