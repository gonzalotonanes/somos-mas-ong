package com.alkemy.ong.domain.news;

import com.alkemy.ong.database.entities.NewsEntity;
import com.alkemy.ong.database.exceptions.DomainException;
import com.alkemy.ong.domain.utils.Page;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DefaultNewService implements NewsService {

    private NewsRepository newsRepository;

    public DefaultNewService(NewsRepository newsRepository) {
        this.newsRepository = newsRepository;
    }

    public Page<NewsModel> getNews(int page) {
        return newsRepository.getNews(page);
    }


    public NewsModel createNews(NewsModel news) {
        return newsRepository.createNews(news);
    }
    private NewsModel toModel(NewsEntity mm){
        NewsModel news = new NewsModel();
        news.setId(mm.getId());
        news.setName(mm.getName());
        news.setCreatedAt(mm.getCreatedAt());
        news.setDeleted(mm.isDeleted());
        news.setContent(mm.getContent());
        news.setImage(mm.getImage());
        news.setUpdatedAt(mm.getUpdatedAt());
        return news;
    }

    @Override
    public NewsModel getNewsById(Long id) {
        return newsRepository.findById(id).orElseThrow(DomainException::new);
    }

    @Override
    public NewsModel updateNews(NewsModel news) {
        Optional<NewsModel> result = newsRepository.findById(news.getId());
        newsRepository.updateNews(news);
        return result.orElseThrow(DomainException::new);
    }

    @Override
    public void deleteNews(Long id) {
        newsRepository.findById(id);
        newsRepository.delete(getNewsById(id));
    }
}
