package com.alkemy.ong.domain.categories;

import java.util.List;

public interface CategoriesRepository {

    List<CategoriesModel> getCategories();
    CategoriesModel createCategory(CategoriesModel category);
    CategoriesModel updateCategories(CategoriesModel categoriesModel, Long categoriesId);
    void deleteCategories(Long categoriesId);
}