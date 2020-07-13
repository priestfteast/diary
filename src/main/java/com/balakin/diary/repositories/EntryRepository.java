package com.balakin.diary.repositories;

import com.balakin.diary.domain.Entry;
import org.springframework.data.repository.CrudRepository;

import java.sql.Date;
import java.util.List;

public interface EntryRepository extends CrudRepository<Entry, Long> {
    List<Entry> findAllByDateBetween(Date startDate, Date endDate);
}
