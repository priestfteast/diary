package com.balakin.diary.services;

import com.balakin.diary.domain.Activity;
import com.balakin.diary.domain.Entry;
import com.balakin.diary.domain.Type;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ChartsServiceImpl implements ChartsService {

    private final EntryService entryService;
    private final ActivityService activityService;

    public ChartsServiceImpl(EntryService entryService, ActivityService activityService) {
        this.entryService = entryService;
        this.activityService = activityService;
    }

    @Override
    public List<Entry> findByPeriod(String start, String end) {

        List<Entry> entries = new ArrayList<>();
        entries = entryService.findByDate(start, end);

        return entries;
    }

    @Override
    public Integer[] getTotalStats(List<Entry> entries){
        Integer[] total = {0,0,0};
        for (Entry e: entries
        ) {
            switch (e.getActivity().getType()){
                case LEISURE:
                    total[0]+=e.getDuration();
                    break;
                case JOB:
                    total[1]+=e.getDuration();
                    break;
                case UPGROWTH:
                    total[2]+=e.getDuration();
                    break;
            }
        }
        return total;
    }

    @Override
    public Map<String, Integer> getDistinctiveStats(List<Entry> entries, Type type) {
        List<Entry> filteredEntries = new ArrayList<>();
        filteredEntries = entries.stream().filter(entry -> entry.getActivity().getType()==type).collect(Collectors.toList());

        Map<String, Integer> activitiesStats = new HashMap<>();
        activityService.getActivities(type).forEach(activity -> activitiesStats.put(activity.getDescription(),0));

        filteredEntries.forEach(entry -> activitiesStats.replace(entry.getActivity().getDescription(), activitiesStats.get(entry.getActivity().getDescription())+entry.getDuration()));


        return activitiesStats;
    }

    @Override
    public int countBusinessDays(String startDate, String endDate) {
        LocalDate date =  LocalDate.parse(startDate);
        int addedDays = 0;

        int allDays = (int) date.until(LocalDate.parse(endDate), ChronoUnit.DAYS);
        for (int i = 0; i <= allDays; i++) {

            if (!(date.getDayOfWeek() == DayOfWeek.SATURDAY || date.getDayOfWeek() == DayOfWeek.SUNDAY)) {
                ++addedDays;
            }
            date=date.plusDays(1);
        }
        return addedDays;
    }

}
