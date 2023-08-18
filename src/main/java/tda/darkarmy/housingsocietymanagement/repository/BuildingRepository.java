package tda.darkarmy.housingsocietymanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tda.darkarmy.housingsocietymanagement.model.Building;
import tda.darkarmy.housingsocietymanagement.model.User;

public interface BuildingRepository extends JpaRepository<Building, Long> {
    Building findByUser(User user);
}
