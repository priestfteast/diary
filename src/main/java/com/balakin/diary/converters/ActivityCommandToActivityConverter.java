package com.balakin.diary.converters;

import com.balakin.diary.commands.ActivityCommand;
import com.balakin.diary.domain.Activity;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class ActivityCommandToActivityConverter implements Converter<ActivityCommand, Activity> {

    private final EntryCommandToEntryConverter entryCommandToEntryConverter;

    public ActivityCommandToActivityConverter(EntryCommandToEntryConverter entryCommandToEntryConverter) {
        this.entryCommandToEntryConverter = entryCommandToEntryConverter;
    }

    @Nullable
    @Synchronized
    @Override
    public Activity convert(ActivityCommand activityCommand) {
        if(activityCommand==null)
        return null;

    Activity activity = new Activity();
    activity.setId(activityCommand.getId());
    activity.setDescription(activityCommand.getDescription());
    activity.setType(activityCommand.getType());
    activity.setLogo(activityCommand.getLogo());
        if(activityCommand.getEntries()!=null&&activityCommand.getEntries().size()>0){
            activityCommand.getEntries().
                    forEach(entry -> activity.getEntries().add(entryCommandToEntryConverter.convert(entry)));
        }


    return activity;
    }
}
