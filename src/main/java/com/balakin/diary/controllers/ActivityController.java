package com.balakin.diary.controllers;

import com.balakin.diary.domain.Activity;
import com.balakin.diary.domain.Type;
import com.balakin.diary.services.ActivityService;
import com.balakin.diary.services.EntryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;


@Controller
public class ActivityController {

    private final ActivityService activityService;
    private final EntryService entryService;

    public ActivityController(ActivityService activityService, EntryService entryService) {
        this.activityService = activityService;
        this.entryService = entryService;
    }


    @GetMapping("/activities/{type}")
    public String showActivities(@PathVariable String type, Model model){
        Type type1 = Type.LEISURE;
        if(type!=null)
            type1=Type.valueOf(type);
        List<Activity> activities = activityService.getActivities(type1);
        model.addAttribute("activities",activities);
        return "activity/show";
    }
}
