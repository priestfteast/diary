package com.balakin.diary.converters;

import com.balakin.diary.commands.ActivityCommand;
import com.balakin.diary.domain.Activity;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class ActivityToActivityCommandConverter implements Converter<Activity, ActivityCommand> {

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


        return activityCommand;
    }
}
