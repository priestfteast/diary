package com.balakin.diary.services;

import com.balakin.diary.commands.EntryCommand;
import com.balakin.diary.converters.EntryCommandToEntryConverter;
import com.balakin.diary.converters.EntryToEntryCommandConverter;
import com.balakin.diary.domain.Entry;
import com.balakin.diary.repositories.ActivityRepository;
import com.balakin.diary.repositories.EntryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EntryServiceImpl implements EntryService {
    private final EntryRepository entryRepository;
    private final EntryToEntryCommandConverter entryToEntryCommandConverter;
    private final EntryCommandToEntryConverter entryCommandToEntryConverter;

    public EntryServiceImpl(EntryRepository entryRepository, ActivityRepository activityRepository, EntryToEntryCommandConverter entryToEntryCommandConverter, EntryCommandToEntryConverter entryCommandToEntryConverter) {
        this.entryRepository = entryRepository;
        this.entryToEntryCommandConverter = entryToEntryCommandConverter;
        this.entryCommandToEntryConverter = entryCommandToEntryConverter;
    }

    @Override
    public EntryCommand findById(Long id) {
        Optional<Entry> entryOptional = entryRepository.findById(id);
        Entry entry = entryOptional.get();

        for (Entry e: entry.getActivity().getEntries()
             ) {
            System.out.println(entry);
            System.out.println(entry.getActivity());
        }
        if(entry!=null) {
            EntryCommand entryCommand = new EntryCommand();
                  entryCommand =  entryToEntryCommandConverter.convert(entry);
            return entryCommand;
        }
        return null;
    }

    @Override
    public List<Entry> findByDate(String startDate, String endDate) {
        List<Entry> entries = new ArrayList<>();
        entries = entryRepository.findAllByDateBetween(LocalDate.parse(startDate), LocalDate.parse(endDate));
        return entries;
    }


    @Transactional
    @Override
    public EntryCommand saveEntryCommand(EntryCommand entryCommand) {
        if(entryCommand==null)
            return null;
        Entry detachedEntry = entryCommandToEntryConverter.convert(entryCommand);
        EntryCommand savedCommand = entryToEntryCommandConverter.convert(entryRepository.save(detachedEntry));
        return savedCommand;
    }

    @Override
    public void deleteById(Long idToDelete) {
        Entry entryToDelete = entryRepository.findById(idToDelete).get();
        entryToDelete.getActivity().getEntries().remove(entryToDelete);
        entryRepository.deleteById(idToDelete);
    }
}
