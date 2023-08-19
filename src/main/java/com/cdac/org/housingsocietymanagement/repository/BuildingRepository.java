package com.cdac.org.housingsocietymanagement.repository;

import com.cdac.org.housingsocietymanagement.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import com.cdac.org.housingsocietymanagement.model.Building;

public interface BuildingRepository extends JpaRepository<Building, Long> {
    Building findByUser(User user);
}
