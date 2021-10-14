package com.alkemy.ong.domain.categories;

import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DefaultCategoriesService implements CategoriesService {

    CategoriesRepository categoriesRepository;

    public DefaultCategoriesService(CategoriesRepository categoriesRepository) {
        this.categoriesRepository = categoriesRepository;
    }

    @Override
    public List<CategoriesModel> getCategories() {
        return categoriesRepository.getCategories();
    }

    @Override
    public CategoriesModel createCategory(CategoriesModel category) {
        return categoriesRepository.createCategory(category);
    }

    @Override
    public CategoriesModel updateCategories(CategoriesModel categoriesModel, Long categoriesId) {
        try {
            return categoriesRepository.updateCategories(categoriesModel, categoriesId);
        } catch (RuntimeException e) {
            throw new CategoriesDomainException();
    }
}

    @Override
    public void deleteCategories(Long categoriesId) {
        try {
            categoriesRepository.deleteCategories(categoriesId);
        } catch (RuntimeException e){
            throw new CategoriesDomainException();
        }
    }
}