package com.alkemy.ong.commons;

import com.alkemy.ong.domain.utils.Page;
import com.alkemy.ong.web.dto.PageDto;
import lombok.Data;

@Data
public class PageUtils {
    public static final int DEFAULT_PAGE_SIZE = 10;

    private static String BASE_URL = "http://localhost:8080/";

    public static PageDto toPageDto(Page<?> page, String urlPath) {
        PageDto dto = new PageDto();
        dto.setContent(page.getContent());
        dto.setNextPage(page.isHasNextPage() ? BASE_URL + urlPath + "?page=" + (page.getCurrentPage() + 1) : null);
        dto.setPreviousPage(page.isHasPreviousPage() ? BASE_URL + urlPath + "?page=" + (page.getCurrentPage() - 1) : null);
        return dto;
    }
}
