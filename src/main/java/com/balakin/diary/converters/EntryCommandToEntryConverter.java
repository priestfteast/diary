package com.balakin.diary.converters;

import com.balakin.diary.commands.EntryCommand;
import com.balakin.diary.domain.Entry;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class EntryCommandToEntryConverter implements Converter<EntryCommand, Entry> {
    @Nullable
    @Synchronized
    @Override
    public Entry convert(EntryCommand entryCommand) {
        if(entryCommand==null)
        return null;

        Entry entry = new Entry();
        entry.setId(entryCommand.getId());
        entry.setActivity(entryCommand.getActivity());
        entry.setDate(entryCommand.getDate());
        entry.setDuration(entryCommand.getDuration());

        return entry;
    }
}
