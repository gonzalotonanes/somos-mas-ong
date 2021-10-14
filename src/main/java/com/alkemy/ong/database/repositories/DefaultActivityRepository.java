package com.alkemy.ong.database.repositories;

import com.alkemy.ong.database.entities.ActivityEntity;
import com.alkemy.ong.database.jparepositories.ActivityJpaRepository;
import com.alkemy.ong.domain.activities.ActivityModel;
import com.alkemy.ong.domain.activities.ActivityRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class DefaultActivityRepository implements ActivityRepository {

    private ActivityJpaRepository activityRepository;

    public DefaultActivityRepository(ActivityJpaRepository activityRepository) {
        this.activityRepository = activityRepository;
    }

    @Override
    public ActivityModel createActivity(ActivityModel activityModel) {

        ActivityEntity activityEntity = this.createEntity(activityModel);
        activityEntity = this.activityRepository.save(activityEntity);
        activityModel.setId(activityEntity.getId());
        return activityModel;
    }

    @Override
    public ActivityModel updateActivity(ActivityModel activityModel) {

        Optional<ActivityEntity> entity = this.activityRepository.findById(activityModel.getId());
        if (!entity.isPresent()) {
            return null;
        }
        this.activityRepository.save(this.modifyEntity(activityModel, entity));
        return activityModel;
    }

    private ActivityEntity createEntity(ActivityModel activityModel) {
        ActivityEntity activityEntity = new ActivityEntity();
        activityEntity.setName(activityModel.getName());
        activityEntity.setContent(activityModel.getContent());
        activityEntity.setImage(activityModel.getImage());
        return activityEntity;
    }

    private ActivityEntity modifyEntity(ActivityModel activityModel, Optional<ActivityEntity> activityEntity) {

        activityEntity.get().setName(activityModel.getName());
        activityEntity.get().setContent(activityModel.getContent());
        activityEntity.get().setImage(activityModel.getImage());
        return activityEntity.get();
    }

}