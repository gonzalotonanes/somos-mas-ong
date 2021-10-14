package com.alkemy.ong.domain.activities;
//arreglar este import

import com.alkemy.ong.domain.exceptions.DomainException;
import org.springframework.stereotype.Service;

@Service
public class DefaultActivityService implements ActivityService {

    private ActivityRepository activityRepository;

    public DefaultActivityService(ActivityRepository activityRepository) {
        this.activityRepository = activityRepository;
    }

    @Override
    public ActivityModel createActivity(ActivityModel activityModel) {
        return activityRepository.createActivity(activityModel);
    }

    @Override
    public ActivityModel updateActivity(ActivityModel activityModel) {

        ActivityModel activityModel1 = activityRepository.updateActivity(activityModel);

        if (activityModel1 == null) {
            throw new DomainException("Activity not found with id: " + activityModel.getId());
        }
        return activityRepository.updateActivity(activityModel);
    }
}
