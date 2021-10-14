package com.alkemy.ong.database.repositories;

import com.alkemy.ong.database.entities.SlideEntity;
import com.alkemy.ong.database.jparepositories.SlideJpaRepository;
import com.alkemy.ong.domain.slides.SlideModel;
import com.alkemy.ong.domain.slides.SlideRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import static java.util.stream.Collectors.toList;

@Repository
public class DefaultSlideRepository implements SlideRepository {
    private SlideJpaRepository repository;

    public DefaultSlideRepository(SlideJpaRepository repository) {
        this.repository = repository;
    }

    private SlideModel toModel(SlideEntity entity) {
        return new SlideModel(
                entity.getId(),
                entity.getIdOrganization(),
                entity.getOrdinalNumber(),
                entity.getText(),
                entity.getImageUrl(),
                entity.isDeleted(),
                entity.getCreatedAt(),
                entity.getUpdatedAt()
        );
    }

    private SlideEntity toEntity(SlideModel model) {
        final SlideEntity slideEntity = new SlideEntity();
        slideEntity.setId(model.getId());
        slideEntity.setIdOrganization(model.getIdOrganization());
        slideEntity.setOrdinalNumber(model.getOrder());
        slideEntity.setImageUrl(model.getImageUrl());
        slideEntity.setText(model.getText());
        slideEntity.setDeleted(model.isDeleted());
        slideEntity.setUpdatedAt(model.getUpdatedAt());
        slideEntity.setCreatedAt(model.getCreatedAt());
        return slideEntity;
    }

    @Override
    public List<SlideModel> getAll() {
        return repository.findAll()
                .stream()
                .map(this::toModel)
                .collect(toList());
    }

    @Override
    public Optional<SlideModel> getById(int id) {
        final Optional<SlideEntity> entity = repository.findById(id);
        return (entity.isPresent()) ? Optional.of(toModel(entity.get())) : Optional.empty();
    }

    @Override
    public SlideModel update(SlideModel slideModel) {
        return toModel(repository.save(toEntity(slideModel)));
    }

    @Override
    public void delete(SlideModel slideModel) {
        repository.delete(toEntity(slideModel));
    }

    @Override
    public List<SlideModel> getAllByOrgId(int orgId) {
        List<SlideEntity> entities = repository.getAllByIdOrganization(orgId);
        return entities
                .stream()
                .map(this::toModel)
                .collect(toList());
    }

    @Override
    public int getNextOrdinalNumber(int idOrg) {
        return repository.getAllByIdOrganization(idOrg).size()+ 1;
    }

    @Override
    public SlideModel create(SlideModel model) {
        SlideEntity entity = new SlideEntity();
        entity.setIdOrganization(model.getIdOrganization());
        entity.setOrdinalNumber(model.getOrder());
        entity.setText(model.getText());
        entity.setImageUrl(model.getImageUrl());
        entity.setCreatedAt(LocalDateTime.now());
        entity.setUpdatedAt(LocalDateTime.now());
        return toModel(repository.save(entity));
    }
}