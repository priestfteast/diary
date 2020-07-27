package com.balakin.diary.services;

import com.balakin.diary.domain.Entry;
import com.balakin.diary.domain.Type;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface ChartsService {
    List<Entry> findByPeriod(String start, String end);
    Integer[] getTotalStats(List<Entry> entries);
    Map<String, Integer> getDistinctiveStats(List<Entry> entries, Type type);
   int countBusinessDays(String startDate, String endDate);

}
