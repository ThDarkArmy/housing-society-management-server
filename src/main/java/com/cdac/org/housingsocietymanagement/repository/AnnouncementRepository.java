package com.cdac.org.housingsocietymanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.cdac.org.housingsocietymanagement.model.Announcement;
import com.cdac.org.housingsocietymanagement.model.Building;

import java.util.List;

public interface AnnouncementRepository extends JpaRepository<Announcement, Long> {
    List<Announcement> findByBuilding(Building building);
}
