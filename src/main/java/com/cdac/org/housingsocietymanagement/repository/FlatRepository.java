package com.cdac.org.housingsocietymanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.cdac.org.housingsocietymanagement.model.Flat;

public interface FlatRepository extends JpaRepository<Flat, Long> {
}
