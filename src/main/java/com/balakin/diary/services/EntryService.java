package com.balakin.diary.services;

import com.balakin.diary.commands.EntryCommand;
import com.balakin.diary.domain.Entry;

import java.util.List;

public interface EntryService {
    EntryCommand findById(Long id);
    List<Entry> findByDate(String startDate, String endDate);
    EntryCommand saveEntryCommand (EntryCommand entryCommand);
    void deleteById(Long idToDelete);
}
