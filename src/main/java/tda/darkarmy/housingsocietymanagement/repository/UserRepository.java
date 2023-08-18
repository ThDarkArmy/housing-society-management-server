package tda.darkarmy.housingsocietymanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tda.darkarmy.housingsocietymanagement.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
