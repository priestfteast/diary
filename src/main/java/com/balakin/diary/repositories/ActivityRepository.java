package com.balakin.diary.repositories;

import com.balakin.diary.domain.Activity;
import com.balakin.diary.domain.Type;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ActivityRepository extends CrudRepository<Activity, Long> {
    List<Activity> findAllByType(Type type);
}
