package com.alkemy.ong.domain.activities;

public interface ActivityRepository {

    public ActivityModel createActivity(ActivityModel activityModel);
    public ActivityModel updateActivity(ActivityModel activityModel);

}
