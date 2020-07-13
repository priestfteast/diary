package com.balakin.diary.services;

import com.balakin.diary.domain.Entry;
import com.balakin.diary.repositories.ActivityRepository;
import com.balakin.diary.repositories.EntryRepository;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class EntryServiceImpl implements EntryService {
    private final EntryRepository entryRepository;
    private final ActivityRepository activityRepository;

    public EntryServiceImpl(EntryRepository entryRepository, ActivityRepository activityRepository) {
        this.entryRepository = entryRepository;
        this.activityRepository = activityRepository;
    }

    @Override
    public List<Entry> findByDate(String startDate, String endDate) {
        List<Entry> entries = new ArrayList<>();
        entries = entryRepository.findAllByDateBetween(Date.valueOf(startDate), Date.valueOf(endDate));
        return entries;

    }
}
