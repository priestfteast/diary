package com.balakin.diary.controllers;

import com.balakin.diary.commands.ActivityCommand;
import com.balakin.diary.commands.EntryCommand;
import com.balakin.diary.domain.Activity;
import com.balakin.diary.domain.Type;
import com.balakin.diary.services.ActivityService;
import com.balakin.diary.services.EntryService;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.List;

@Controller
public class EntryController {

    private final EntryService entryService;
    private final ActivityService activityService;

    public EntryController(EntryService entryService, ActivityService activityService) {
        this.entryService = entryService;
        this.activityService = activityService;
    }


    @GetMapping("/entries/show/today")
    public String showEntries(Model model){
        model.addAttribute("entries",entryService.findByDate(LocalDate.now().toString(),LocalDate.now().toString()));
        model.addAttribute("start", LocalDate.now().toString());
        model.addAttribute("end", LocalDate.now().toString());
        return "entry/show";
    }

    @RequestMapping("/entries/show/{startDate}/{endDate}")
    public String showEntries(@PathVariable String startDate, @PathVariable String endDate, Model model){
        model.addAttribute("entries",entryService.findByDate(startDate,endDate));
        model.addAttribute("start", startDate);
        model.addAttribute("end", endDate);
        return "entry/show";
    }

    @PostMapping("entries/search")
    public String viewEntries(@RequestParam MultiValueMap<String, String> formData)  {

        String startDate = formData.getFirst("startdate");
        String endDate = formData.getFirst("enddate");
            return "redirect:/entries/show/"+startDate+"/"+endDate;
        }

    @GetMapping("/entries/new/{type}")
    public String newEntry(@PathVariable String type, Model model){
        EntryCommand entryCommand = new EntryCommand();
        entryCommand.setDate(LocalDate.now());
        model.addAttribute("entry",entryCommand);
        model.addAttribute("activities",activityService.getActivities(Type.valueOf(type)));

        return "entry/edit";
    }

        @GetMapping("/entries/{id}/edit/{type}")
        public String editEntry(@PathVariable String type,@PathVariable String id, Model model){

        if(id.equals("null"))
            return "redirect:/entries/new/"+type;

            List<Activity> activities = activityService.getActivities(Type.valueOf(type));
            model.addAttribute("entry",entryService.findById(Long.valueOf(id)) );
            model.addAttribute("activities",activities);

            return "entry/edit";
        }

    @GetMapping("entries/{id}/delete")
    public String deleteEntry(@PathVariable String id){
        LocalDate date = entryService.findById(Long.valueOf(id)).getDate();
        entryService.deleteById(Long.valueOf(id));
        return "redirect:/entries/show/"+date.toString()+"/"+date.toString();
    }

        @PostMapping("/entries/{id}/save")
        public String saveOrUpdate(@Valid @ModelAttribute ("entry") EntryCommand entryCommand, BindingResult bindingResult, Model model) {
            if (bindingResult.hasErrors()) {
                bindingResult.getAllErrors().forEach(objectError -> {
                });
                model.addAttribute("activities",activityService.getActivities(entryCommand.getActivity().getType()));
                return "entry/edit";
            }

           EntryCommand savedEntryCommand = entryService.saveEntryCommand(entryCommand);
           return "redirect:/entries/show/"+savedEntryCommand.getDate()+"/"+savedEntryCommand.getDate();
        }

    @GetMapping("entries/{id}/getimage")
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
