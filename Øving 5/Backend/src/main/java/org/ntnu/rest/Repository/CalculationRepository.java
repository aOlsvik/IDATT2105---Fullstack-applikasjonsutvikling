package org.ntnu.rest.Repository;

import org.ntnu.rest.Entity.Calculation;
import org.ntnu.rest.Entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CalculationRepository extends JpaRepository<Calculation, Integer> {
    Page<Calculation> findByUserOrderByIdDesc(User user, Pageable pageable);
}
