package com.balakin.diary.services;

import com.balakin.diary.domain.Entry;

import java.util.List;

public interface EntryService {
    List<Entry> findByDate(String startDate, String endDate);
}
