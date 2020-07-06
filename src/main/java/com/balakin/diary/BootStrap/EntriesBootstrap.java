package com.balakin.diary.BootStrap;

import com.balakin.diary.domain.Activity;
import com.balakin.diary.domain.Entry;
import com.balakin.diary.domain.Type;
import com.balakin.diary.repositories.ActivityRepository;
import com.balakin.diary.repositories.EntryRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class EntriesBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private final EntryRepository entryRepository;
    private final ActivityRepository activityRepository;

    public EntriesBootstrap(EntryRepository entryRepository, ActivityRepository activityRepository) {
        this.entryRepository = entryRepository;
        this.activityRepository = activityRepository;
    }


    private List<Entry> getEntries() {
        List<Entry> entries = new ArrayList<>(2);

        if (entryRepository.findAll().iterator().hasNext())
            return entries;

        Entry entry1 = new Entry();
        Activity activity1 = new Activity();
        activity1.setDescription("VK");
        activity1.setType(Type.LEISURE);
        entry1.setDate(Date.valueOf(LocalDate.now()));
        entry1.setDuration(20);
        entry1.setActivity(activity1);

        Entry entry2 = new Entry();
        Activity activity2 = new Activity();
        activity2.setDescription("English");
        activity2.setType(Type.UPGROWTH);
        entry2.setDate(Date.valueOf(LocalDate.now()));
        entry2.setDuration(20);
        entry2.setActivity(activity2);

        activityRepository.save(activity1);
        activityRepository.save(activity2);
        entries.add(entry1);
        entries.add(entry2);


        return entries;
    }

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        entryRepository.saveAll(getEntries());
    }


}
