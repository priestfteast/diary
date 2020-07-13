package com.balakin.diary.converters;

import com.balakin.diary.commands.ActivityCommand;
import com.balakin.diary.domain.Activity;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class ActivityCommandToActivityConverter implements Converter<ActivityCommand, Activity> {

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


    return activity;
    }
}
