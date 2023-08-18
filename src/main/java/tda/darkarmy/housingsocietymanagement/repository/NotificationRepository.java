package tda.darkarmy.housingsocietymanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tda.darkarmy.housingsocietymanagement.model.Notification;
import tda.darkarmy.housingsocietymanagement.model.User;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
    List<Notification> findByUser(User user);
}
