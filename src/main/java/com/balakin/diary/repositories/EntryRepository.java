package com.balakin.diary.repositories;

import com.balakin.diary.domain.Entry;
import org.springframework.data.repository.CrudRepository;

public interface EntryRepository extends CrudRepository<Entry, Long> {
}
