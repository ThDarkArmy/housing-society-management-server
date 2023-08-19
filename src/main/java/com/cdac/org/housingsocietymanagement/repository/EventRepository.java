package com.cdac.org.housingsocietymanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.cdac.org.housingsocietymanagement.model.Event;

public interface EventRepository extends JpaRepository<Event, Long> {
}
