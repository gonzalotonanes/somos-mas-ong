package com.alkemy.ong.database.repositories;

import com.alkemy.ong.database.entities.CategoriesEntity;
import com.alkemy.ong.database.jparepositories.CategoriesJpaRepo;
import com.alkemy.ong.domain.categories.CategoriesModel;
import com.alkemy.ong.domain.categories.CategoriesRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class DefaultCategoriesRepo implements CategoriesRepository {

    private CategoriesJpaRepo categoriesJPARepo;

    public DefaultCategoriesRepo(CategoriesJpaRepo categoriesJPARepo) {
        this.categoriesJPARepo = categoriesJPARepo;
    }

    @Override
    public List<CategoriesModel> getCategories() {
        List<CategoriesEntity> categories = categoriesJPARepo.findAll();
        return categories.stream().map(MemberEntity -> toModel(MemberEntity)).collect(Collectors.toList());
    }

    public CategoriesModel convertToModel(CategoriesEntity categoriesEntity) {
        CategoriesModel categoriesModel = new CategoriesModel();

        categoriesModel.setId(categoriesEntity.getId());
        categoriesModel.setName(categoriesEntity.getName());
        categoriesModel.setDescription(categoriesEntity.getDescription());
        categoriesModel.setImage(categoriesEntity.getImage());
        categoriesModel.setDeleted(categoriesEntity.isDeleted());
        categoriesModel.setCreatedAt(categoriesEntity.getCreatedAt());
        categoriesModel.setUpdatedAt(categoriesEntity.getUpdatedAt());
        return categoriesModel;
    }

    @Override
    public CategoriesModel createCategory(CategoriesModel category) {
        categoriesJPARepo.save(this.toEntity(category));
        return category;
    }

    @Override
    public CategoriesModel updateCategories(CategoriesModel categoriesModel, Long categoriesId) {
        Optional<CategoriesEntity> categories = categoriesJPARepo.findById(categoriesId);

        categories.get().setName(categoriesModel.getName());
        categories.get().setDescription(categoriesModel.getDescription());
        categories.get().setImage(categoriesModel.getImage());
        categories.get().setDeleted(categoriesModel.isDeleted());
        categories.get().setCreatedAt(categoriesModel.getCreatedAt());
        categories.get().setUpdatedAt(categoriesModel.getUpdatedAt());

        categoriesJPARepo.save(categories.get());

        categoriesModel = convertToModel(categories.get());

        return categoriesModel;
    }

    @Override
    public void deleteCategories(Long categoriesId) {
        categoriesJPARepo.deleteById(categoriesId);
    }

    private CategoriesEntity toEntity(CategoriesModel cm) {
        CategoriesEntity categoriesEntity = new CategoriesEntity();
        categoriesEntity.setName(cm.getName());
        categoriesEntity.setDescription(cm.getDescription());
        categoriesEntity.setImage(cm.getImage());
        return categoriesEntity;
    }

    public CategoriesModel toModel(CategoriesEntity categoriesEntity) {
        CategoriesModel categoriesModel = new CategoriesModel();

        categoriesModel.setId(categoriesEntity.getId());
        categoriesModel.setName(categoriesEntity.getName());
        categoriesModel.setDescription(categoriesEntity.getDescription());
        categoriesModel.setImage(categoriesEntity.getImage());
        categoriesModel.setDeleted(categoriesEntity.isDeleted());
        categoriesModel.setCreatedAt(categoriesEntity.getCreatedAt());
        categoriesModel.setUpdatedAt(categoriesEntity.getUpdatedAt());
        return categoriesModel;
    }
}
