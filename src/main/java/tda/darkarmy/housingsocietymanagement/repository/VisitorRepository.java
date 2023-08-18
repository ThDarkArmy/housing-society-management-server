package tda.darkarmy.housingsocietymanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tda.darkarmy.housingsocietymanagement.model.Visitor;

public interface VisitorRepository extends JpaRepository<Visitor, Long> {
}
