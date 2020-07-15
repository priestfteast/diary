package com.balakin.diary.controllers;

import com.balakin.diary.commands.ActivityCommand;
import com.balakin.diary.domain.Activity;
import com.balakin.diary.domain.Type;
import com.balakin.diary.services.ActivityServiceImpl;
import com.balakin.diary.services.EntryServiceImpl;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;


@Controller
public class ActivityController {

    private final ActivityServiceImpl activityService;
    private final EntryServiceImpl entryService;

    public ActivityController(ActivityServiceImpl activityService, EntryServiceImpl entryService) {
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
        activities.forEach(activity -> activity.getEntries().forEach(entry -> System.out.println(entry)));
        return "activity/show";
    }

    @GetMapping({"/activities/new"})
    public String newActivity(Model model){
        model.addAttribute("activity", new ActivityCommand());
        return "activity/edit";
    }

    @GetMapping({"activities/{id}/edit"})
    public String editActivity(@PathVariable String id, Model model){
        model.addAttribute("activity",activityService.findById(Long.valueOf(id)) );
        return "activity/edit";
    }

    @PostMapping("activities/{id}/save")
    public String saveOrUpdateActivity(@Valid @ModelAttribute("activity") ActivityCommand command, BindingResult bindingResult, Model model){

        if(bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(objectError -> {
                System.out.println(objectError.toString());
            });

            return "activity/edit";
        }

        ActivityCommand savedCommand = activityService.saveActivityCommand(command);

        return "redirect:/activities/" + savedCommand.getType().name();
        }

        @PostMapping("activities/{id}/saveimage")
        public String saveLogo(@PathVariable String id, @RequestParam("imagefile") MultipartFile file, Model model){
        activityService.saveLogoFile(Long.valueOf(id),file);


            return "redirect:/activities/"+id+"/edit";
        }

        @GetMapping("activities/{id}/delete")
        public String deleteActivity(@PathVariable String id){
            Type type = activityService.findById(Long.valueOf(id)).getType();
            activityService.deleteById(Long.valueOf(id));

            return "redirect:/activities/"+type.toString();
        }

    @GetMapping("activities/{id}/getimage")
    public void renderImageFromDB(@PathVariable String id, HttpServletResponse response) throws IOException {
        ActivityCommand activityCommand = activityService.findById(Long.valueOf(id));

        if (activityCommand.getLogo() != null) {
            byte[] byteArray = new byte[activityCommand.getLogo().length];
            int i = 0;

            for (Byte wrappedByte : activityCommand.getLogo()){
                byteArray[i++] = wrappedByte; //auto unboxing
            }

            response.setContentType("image/jpeg");
            InputStream is = new ByteArrayInputStream(byteArray);
            IOUtils.copy(is, response.getOutputStream());
        }
    }
}
