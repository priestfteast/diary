package com.balakin.diary.services;

import com.balakin.diary.commands.ActivityCommand;
import com.balakin.diary.domain.Activity;
import com.balakin.diary.domain.Type;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ActivityService {
    List<Activity> getActivities(Type type);
    ActivityCommand findById(Long id);
    ActivityCommand saveActivityCommand(ActivityCommand activityCommand);
    void saveLogoFile(Long id, MultipartFile file);
}
