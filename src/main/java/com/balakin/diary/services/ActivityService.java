package com.balakin.diary.services;

import com.balakin.diary.repositories.ActivityRepository;
import com.balakin.diary.repositories.EntryRepository;
import org.springframework.stereotype.Service;

@Service
public class ActivityService {
    private final ActivityRepository activityRepository;
    private final EntryRepository entryRepository;

    public ActivityService(ActivityRepository activityRepository, EntryRepository entryRepository) {
        this.activityRepository = activityRepository;
        this.entryRepository = entryRepository;
    }
}
