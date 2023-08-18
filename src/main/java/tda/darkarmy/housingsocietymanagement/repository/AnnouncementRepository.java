package tda.darkarmy.housingsocietymanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tda.darkarmy.housingsocietymanagement.model.Announcement;

public interface AnnouncementRepository extends JpaRepository<Announcement, Long> {
}
