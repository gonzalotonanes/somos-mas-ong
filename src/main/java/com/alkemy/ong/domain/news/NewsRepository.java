package com.alkemy.ong.domain.news;

import com.alkemy.ong.domain.utils.Page;

import java.util.Optional;

public interface NewsRepository  {
    Page<NewsModel> getNews(int page);
    Optional<NewsModel> findById(Long id);
    NewsModel createNews(NewsModel news);
    NewsModel updateNews(NewsModel news);
    void delete(NewsModel news);
}
