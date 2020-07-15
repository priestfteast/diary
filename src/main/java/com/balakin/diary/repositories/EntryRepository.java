package com.balakin.diary.repositories;

import com.balakin.diary.domain.Entry;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.List;

public interface EntryRepository extends CrudRepository<Entry, Long> {
    List<Entry> findAllByDateBetween(LocalDate startDate, LocalDate endDate);
}
