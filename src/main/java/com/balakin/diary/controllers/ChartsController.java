package com.balakin.diary.controllers;

import com.balakin.diary.domain.Entry;
import com.balakin.diary.domain.Type;
import com.balakin.diary.services.ChartsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.time.DayOfWeek.MONDAY;
import static java.time.DayOfWeek.SUNDAY;
import static java.time.temporal.TemporalAdjusters.*;

@Controller
public class ChartsController {

    private final ChartsService chartsService;

    public ChartsController(ChartsService chartsService) {
        this.chartsService = chartsService;
    }

    @GetMapping({"/charts/show/today","/index","/"})
    public String showCharts( Model model){


        return "redirect:/charts/show/"+LocalDate.now().toString()+"/"+LocalDate.now().toString();
    }

    @GetMapping("/charts/show/{startDate}/{endDate}")
    public String showCharts(@PathVariable String startDate, @PathVariable String endDate, Model model){
        List<Entry> entries = new ArrayList<>();
        entries = chartsService.findByPeriod(startDate,endDate);
        Type[] types ={Type.LEISURE,Type.JOB,Type.UPGROWTH};
        Integer[] total = chartsService.getTotalStats(entries);
        Map<String, Integer> leisureStats = chartsService.getDistinctiveStats(entries,Type.LEISURE);
        Map<String, Integer> jobStats = chartsService.getDistinctiveStats(entries,Type.JOB);
        Map<String, Integer> upgrowthStats = chartsService.getDistinctiveStats(entries,Type.UPGROWTH);
        int businessDays = chartsService.countBusinessDays(startDate,endDate);
        Integer[] standart = {90, 240};
        int daysLeft = (int) LocalDate.now().until(LocalDate.parse(endDate), ChronoUnit.DAYS);



        model.addAttribute("leisureactivities",leisureStats.keySet());
        model.addAttribute("jobactivities",jobStats.keySet());
        model.addAttribute("upgrowthactivities",upgrowthStats.keySet());
        model.addAttribute("leisuretime",leisureStats.values());
        model.addAttribute("jobtime",jobStats.values());
        model.addAttribute("upgrowthtime",upgrowthStats.values());
        model.addAttribute("entries",entries);
        model.addAttribute("total",total);
        model.addAttribute("types",types);
        model.addAttribute("start", LocalDate.parse(startDate));
        model.addAttribute("end", LocalDate.parse(endDate));
        model.addAttribute("standart",standart);
        model.addAttribute("businessdays",businessDays);
        model.addAttribute("daysleft",daysLeft);
        System.out.println(businessDays);
        return "charts/show";
    }

    @PostMapping("charts/search/{period}")
    public String viewEntries(@PathVariable String period, @RequestParam MultiValueMap<String, String> formData)  {
        LocalDate now = LocalDate.now();
        LocalDate startDate = LocalDate.now() ;
        LocalDate endDate = LocalDate.now();

        switch (period) {
            case "period":
                startDate = LocalDate.parse(formData.getFirst("startdate"));
                endDate = LocalDate.parse(formData.getFirst("enddate"));
                break;
            case "week":
                startDate = now.with(previousOrSame(MONDAY));
                endDate = now.with(nextOrSame(SUNDAY));
                break;
            case "month":
                startDate = now.with(firstDayOfMonth());
                endDate = now.with(lastDayOfMonth());
                break;
            case "quarter":
                startDate = now.with(now.getMonth().firstMonthOfQuarter())
                        .with(TemporalAdjusters.firstDayOfMonth());
                endDate = startDate.plusMonths(2)
                        .with(TemporalAdjusters.lastDayOfMonth());
                break;
            case "year":
                startDate = now.with(firstDayOfYear());
                endDate = now.with(lastDayOfYear());
                break;
        }


        return "redirect:/charts/show/"+startDate.toString()+"/"+endDate.toString();
    }
}
