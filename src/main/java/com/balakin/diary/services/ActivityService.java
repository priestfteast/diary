package com.balakin.diary.services;

import com.balakin.diary.domain.Activity;
import com.balakin.diary.domain.Type;
import com.balakin.diary.repositories.ActivityRepository;
import com.balakin.diary.repositories.EntryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActivityService {
    private final ActivityRepository activityRepository;
    private final EntryRepository entryRepository;

    public ActivityService(ActivityRepository activityRepository, EntryRepository entryRepository) {
        this.activityRepository = activityRepository;
        this.entryRepository = entryRepository;
    }

    public List<Activity> getActivities(Type type){
        return activityRepository.findAllByType(type);
    }
}
