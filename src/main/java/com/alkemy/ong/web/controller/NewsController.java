package com.alkemy.ong.web.controller;

import com.alkemy.ong.domain.news.NewsModel;
import com.alkemy.ong.domain.news.NewsService;
import com.alkemy.ong.domain.utils.Page;
import com.alkemy.ong.web.dto.NewsDto;
import com.alkemy.ong.web.dto.PageDto;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.alkemy.ong.commons.PageUtils.toPageDto;

@RestController
@RequestMapping(path = "/news")
public class NewsController {

    private NewsService newsService;

    public NewsController(NewsService newsService) {
        this.newsService = newsService;
    }

    @GetMapping
    public ResponseEntity<PageDto> getNews(@RequestParam("page") int page) {
        //List<NewsDto> result = newsService.getNews().stream().map(m -> toDto(m)).collect(Collectors.toList());
        Page<NewsModel> news = newsService.getNews(page);

        return new ResponseEntity<PageDto>(toPageDto(news, "news"), HttpStatus.OK);
    }

    @ApiOperation(value = "", authorizations = { @Authorization(value="jwtToken") })
    @PreAuthorize("hasAuthority('Admin')")
    @PostMapping
    public ResponseEntity<NewsDto> createNews(@Valid @RequestBody NewsDto news) {
        return new ResponseEntity<NewsDto>(this.toDto(newsService.createNews(toModel(news))), HttpStatus.CREATED);
    }

    @PutMapping(path = "{news.getId()}")
    public ResponseEntity<NewsDto> updateNews(@Valid @RequestBody NewsModel news) {
        newsService.updateNews(news);

        NewsDto newsDto = new NewsDto(news.getId(),news.getIdCategory(), news.getName(), news.getContent(),
                news.getImage(), news.getCreatedAt(), news.getUpdatedAt());

        return new ResponseEntity<NewsDto>(newsDto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long newsId) {
        newsService.deleteNews(newsId);
        return ResponseEntity.noContent().build();
    }

    private NewsDto toDto(NewsModel news) {
        return new NewsDto(news.getId(), news.getIdCategory(),news.getName(), news.getImage(), news.getContent(),
                news.isDeleted(), news.getCreatedAt(), news.getUpdatedAt());
    }

    private NewsModel toModel(NewsDto dto) {
        NewsModel news = new NewsModel();
        news.setId(dto.getId());
        news.setIdCategory(dto.getIdCategory());
        news.setName(dto.getName());
        news.setContent(dto.getContent());
        news.setImage(dto.getImage());
        news.setDeleted(dto.isDeleted());
        news.setCreatedAt(dto.getCreatedAt());
        news.setUpdatedAt(dto.getUpdatedAt());

        return news;
    }
}
