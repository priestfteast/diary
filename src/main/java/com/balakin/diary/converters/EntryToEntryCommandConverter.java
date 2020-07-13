package com.balakin.diary.converters;

import com.balakin.diary.commands.EntryCommand;
import com.balakin.diary.domain.Entry;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class EntryToEntryCommandConverter implements Converter<Entry, EntryCommand> {
    @Nullable
    @Synchronized
    @Override
    public EntryCommand convert(Entry entry) {
        if(entry==null)
            return null;

        EntryCommand entryCommand = new EntryCommand();
        entryCommand.setId(entry.getId());
        entryCommand.setActivity(entry.getActivity());
        entryCommand.setDate(entry.getDate());
        entryCommand.setDuration(entry.getDuration());


        return entryCommand;
    }
}
