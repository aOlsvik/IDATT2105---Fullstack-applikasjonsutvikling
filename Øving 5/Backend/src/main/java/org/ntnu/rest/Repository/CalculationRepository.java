package org.ntnu.rest.Repository;

import org.ntnu.rest.Entity.Calculation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CalculationRepository extends JpaRepository<Calculation, Integer> {
}
