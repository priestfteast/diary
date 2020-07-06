package com.balakin.diary.services;

import com.balakin.diary.repositories.ActivityRepository;
import com.balakin.diary.repositories.EntryRepository;
import org.springframework.stereotype.Service;

@Service
public class EntryService {
    private final EntryRepository entryRepository;
    private final ActivityRepository activityRepository;

    public EntryService(EntryRepository entryRepository, ActivityRepository activityRepository) {
        this.entryRepository = entryRepository;
        this.activityRepository = activityRepository;
    }
}
