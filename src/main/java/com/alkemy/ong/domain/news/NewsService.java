package com.alkemy.ong.domain.news;

import com.alkemy.ong.domain.utils.Page;

public interface NewsService {
     Page<NewsModel> getNews(int page);
     NewsModel createNews (NewsModel news);
     NewsModel getNewsById(Long id);
     NewsModel updateNews(NewsModel news);
     void deleteNews(Long id);
}
