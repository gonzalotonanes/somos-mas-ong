package com.alkemy.ong.database.repositories;

import com.alkemy.ong.database.entities.NewsEntity;
import com.alkemy.ong.database.exceptions.DomainException;
import com.alkemy.ong.database.jparepositories.NewsJPARepository;
import com.alkemy.ong.database.exceptions.DomainException;
import com.alkemy.ong.domain.news.NewsModel;
import com.alkemy.ong.domain.news.NewsRepository;
import com.alkemy.ong.domain.utils.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import static com.alkemy.ong.commons.PageUtils.DEFAULT_PAGE_SIZE;
import static java.util.stream.Collectors.toList;

@Repository
public class DefaultNewsRepository implements NewsRepository {

    private NewsJPARepository newsRepository;

    public DefaultNewsRepository(NewsJPARepository newsRepository) {
        this.newsRepository = newsRepository;
    }

    @Override
    public Page<NewsModel> getNews(int page) {
        Pageable pageable = PageRequest.of(page, DEFAULT_PAGE_SIZE);
        org.springframework.data.domain.Page<NewsEntity> news = newsRepository.findAll(pageable);

        return toPage(news);
    }

    @Override
    public Optional<NewsModel> findById(Long id) {
        return newsRepository.findById(id)
                .map(this::toModel);
    }

    public NewsModel createNews(NewsModel news) {
        newsRepository.save(this.toEntity(news));
        return news;
    }

    @Override

    public NewsModel updateNews(NewsModel newsModel) {
        Optional<NewsEntity> news = newsRepository.findById(newsModel.getId());
        if (!news.isEmpty())
                newsRepository.save(toEntity(newsModel));
        return toModel(news.orElseThrow(DomainException::new));
    }


    public void delete(NewsModel news) {
        newsRepository.delete(toEntity(news));
    }


    private NewsEntity toEntity(NewsModel newsModel) {
        NewsEntity news = new NewsEntity();
        news.setId(newsModel.getId());
        news.setIdCategory(newsModel.getIdCategory());
        news.setName(newsModel.getName());
        news.setContent(newsModel.getContent());
        news.setImage(newsModel.getImage());
        news.setDeleted(newsModel.isDeleted());
        news.setCreatedAt(newsModel.getCreatedAt());
        news.setUpdatedAt(newsModel.getUpdatedAt());
        return news;
    }

    private NewsModel toModel(NewsEntity newsEntity) {
        NewsModel newsModel = new NewsModel();
        newsModel.setId(newsEntity.getId());
        newsModel.setIdCategory(newsEntity.getIdCategory());
        newsModel.setName(newsEntity.getName());
        newsModel.setContent(newsEntity.getContent());
        newsModel.setImage(newsEntity.getImage());

        return newsModel;
    }

    private Page<NewsModel> toPage(org.springframework.data.domain.Page<NewsEntity> news) {
        Page<NewsModel> page = new Page<>();
        page.setContent(news.getContent().stream()
                .map(this::toModel).collect(toList()));
        page.setHasNextPage(news.hasNext());
        page.setHasPreviousPage(news.hasPrevious());
        page.setCurrentPage(news.getPageable().getPageNumber());
        return page;
    }
}
