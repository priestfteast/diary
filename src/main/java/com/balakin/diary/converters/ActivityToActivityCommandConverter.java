package com.balakin.diary.converters;

import com.balakin.diary.commands.ActivityCommand;
import com.balakin.diary.domain.Activity;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class ActivityToActivityCommandConverter implements Converter<Activity, ActivityCommand> {

    private final EntryToEntryCommandConverter entryToEntryCommandConverter;

    public ActivityToActivityCommandConverter(EntryToEntryCommandConverter entryToEntryCommandConverter) {
        this.entryToEntryCommandConverter = entryToEntryCommandConverter;
    }

    @Nullable
    @Synchronized
    @Override
    public ActivityCommand convert(Activity activity) {
        if(activity==null)
            return null;
        ActivityCommand activityCommand = new ActivityCommand();
        activityCommand.setId(activity.getId());
        activityCommand.setDescription(activity.getDescription());
        activityCommand.setType(activity.getType());
        activityCommand.setLogo(activity.getLogo());
        if(activity.getEntries()!=null&&activity.getEntries().size()>0){
            activity.getEntries().
                    forEach(entry -> activityCommand.getEntries().add(entryToEntryCommandConverter.convert(entry)));
        }



        return activityCommand;
    }
}
