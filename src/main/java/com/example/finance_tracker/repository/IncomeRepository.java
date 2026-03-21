package com.example.finance_tracker.repository;

import com.example.finance_tracker.model.Income;
import com.example.finance_tracker.model.User;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IncomeRepository extends JpaRepository<Income, Long> {
  Optional<Income> findByIdAndUserId(Long id, Long userId);
  List<Income> findByUser(User user);
}
