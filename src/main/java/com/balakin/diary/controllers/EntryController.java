package com.balakin.diary.controllers;

import com.balakin.diary.services.EntryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;

@Controller
public class EntryController {

    private final EntryService entryService;

    public EntryController(EntryService entryService) {
        this.entryService = entryService;
    }


    @RequestMapping("/entries/show/today")
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

}
