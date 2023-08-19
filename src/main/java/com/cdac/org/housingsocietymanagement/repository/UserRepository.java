package com.cdac.org.housingsocietymanagement.repository;

import com.cdac.org.housingsocietymanagement.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
