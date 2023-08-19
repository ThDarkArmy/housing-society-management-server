package com.cdac.org.housingsocietymanagement.repository;

import com.cdac.org.housingsocietymanagement.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import com.cdac.org.housingsocietymanagement.model.Notification;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
    List<Notification> findByUser(User user);
}
