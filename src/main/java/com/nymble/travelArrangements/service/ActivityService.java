package com.nymble.travelArrangements.service;

import com.nymble.travelArrangements.entity.Activity;
import com.nymble.travelArrangements.repository.ActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActivityService {

    @Autowired
    private ActivityRepository activityRepository;

    public List<Activity> getAvailableActivities() {
        return activityRepository.findAvailableActivities();
    }
}
