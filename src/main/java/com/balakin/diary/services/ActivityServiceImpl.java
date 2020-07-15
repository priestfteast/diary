package com.balakin.diary.services;

import com.balakin.diary.commands.ActivityCommand;
import com.balakin.diary.converters.ActivityCommandToActivityConverter;
import com.balakin.diary.converters.ActivityToActivityCommandConverter;
import com.balakin.diary.domain.Activity;
import com.balakin.diary.domain.Type;
import com.balakin.diary.repositories.ActivityRepository;
import com.balakin.diary.repositories.EntryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class ActivityServiceImpl implements ActivityService {
    private final ActivityRepository activityRepository;
    private final EntryRepository entryRepository;
    private final ActivityToActivityCommandConverter activityToActivityCommandConverter;
    private final ActivityCommandToActivityConverter activityCommandToActivityConverter;

    public ActivityServiceImpl(ActivityRepository activityRepository, EntryRepository entryRepository, ActivityToActivityCommandConverter activityToActivityCommandConverter, ActivityCommandToActivityConverter activityCommandToActivityConverter) {
        this.activityRepository = activityRepository;
        this.entryRepository = entryRepository;
        this.activityToActivityCommandConverter = activityToActivityCommandConverter;
        this.activityCommandToActivityConverter = activityCommandToActivityConverter;
    }

    public List<Activity> getActivities(Type type) {
        return activityRepository.findAllByType(type);
    }

    @Override
    public ActivityCommand findById(Long id) {
        Optional<Activity> activityOptional = activityRepository.findById(id);

        Activity activity = activityOptional.get();

        ActivityCommand activityCommand = activityToActivityCommandConverter.convert(activity);

        return activityCommand;
    }

    @Override
    @Transactional
    public ActivityCommand saveActivityCommand(ActivityCommand activityCommand) {
        if(activityCommand.getId()!=null && activityRepository.findById(activityCommand.getId()).isPresent())
            activityCommand.setLogo(activityRepository.findById(activityCommand.getId()).get().getLogo());
        Activity detachedActivity = activityCommandToActivityConverter.convert(activityCommand);
        Activity savedActivity= activityRepository.save(detachedActivity);
       return activityToActivityCommandConverter.convert(savedActivity);

        }

    @Override
    public void saveLogoFile(Long id, MultipartFile file) {

        if(file.getSize()>5000000) {

            throw new IllegalStateException("Размер изображения превышает 5 мб");
        }


        try{
            Activity activity = activityRepository.findById(id).get();
            Byte[] byteObjects = new Byte[file.getBytes().length];

            int counter = 0;

            for (Byte b: file.getBytes()
            ) {
                byteObjects[counter++] =b;
            }
            activity.setLogo(byteObjects);
            activityRepository.save(activity);
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void deleteById(Long idToDelete) {
        activityRepository.deleteById(idToDelete);
    }
}
