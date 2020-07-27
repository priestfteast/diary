package com.balakin.diary;

import com.balakin.diary.commands.EntryCommand;
import com.balakin.diary.domain.Entry;
import com.balakin.diary.services.ActivityService;
import com.balakin.diary.services.EntryService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

@SpringBootApplication
public class DiaryApplication {

    private final ActivityService activityService;
    private final EntryService entryService;

    public DiaryApplication(ActivityService activityService, EntryService entryService) {
        this.activityService = activityService;
        this.entryService = entryService;
    }

    public static void main(String[] args) {
        SpringApplication.run(DiaryApplication.class, args);
    }
    @PostConstruct
    public void init(){
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));   // It will set UTC timezone
        System.out.println("Spring boot application running in UTC timezone :"+LocalDate.now());   // It will print UTC timezone
//        File file = new File("C:/users/Jeremy/Desktop/Diary.txt");
//        List<List<EntryCommand>> list = new ArrayList<>();
//        try {
//            BufferedReader br = new BufferedReader(new FileReader(file));
//            String date = "";
//            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
//            List<EntryCommand> list2 = new ArrayList<>();
//            while (br.ready()){
//                String line = br.readLine();
//
//                if(line.matches("^[0-9]{2}\\.[0-9]{2}\\.[0-9]{4}$")){
//                    if(!list2.isEmpty()) {
//                        list.add(list2);
//                    }
//                    list2 = new ArrayList<>();
//                    date = line;
//
//                }
//                if(line.length()>12){
//                    EntryCommand entry = new EntryCommand();
//                    entry.setDate(LocalDate.parse(date, formatter));
//                    entry.setDuration(Integer.valueOf(line.substring(0,line.indexOf(" "))));
//                    entry.setActivity(activityService.findByDescription(getDescription(line.substring(line.indexOf("(")+1,line.lastIndexOf(")")))));
//                    list2.add(entry);
//
//                }
//            }
//            if(!list2.isEmpty())
//                list.add(list2);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//
//        for (List<EntryCommand> l: list){
//            for (EntryCommand e: l
//                 ) {
//
//                entryService.saveEntryCommand(e);
//            }
//        }
    }

//    public String getDescription (String description){
//
//        String activity = "";
//
//        switch (description){
//            case "гитара":
//                activity = "Guitar";
//                break;
//            case "планерка":
//                activity = "Planning";
//                break;
//            case "переписка":
//                activity = "Mail";
//                break;
//            case "документы":
//                activity = "Documents";
//                break;
//            case "english":
//                activity = "English";
//                break;
//            case "представительство":
//                activity = "Representation";
//                break;
//            case "география":
//                activity = "Geography";
//                break;
//            case "история":
//                activity = "History";
//                break;
//            case "vk":
//                activity = "VK";
//                break;
//            case "ютуб":
//                activity = "YouTube";
//                break;
//            case "игры":
//                activity = "PC Games";
//                break;
//            case "программирование":
//                activity = "Coding";
//                break;
//            case "книги":
//                activity = "Books";
//                break;
//            case "чтение":
//                activity = "Reading";
//                break;
//            case "кино":
//                activity = "Moovies";
//                break;
//            case "обучение":
//                activity = "Training";
//                break;
//                default:
//                    try {
//                        throw new Exception("No match for "+description);
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//
//        }
//        return activity;
//    }
}

