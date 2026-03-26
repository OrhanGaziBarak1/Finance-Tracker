package com.example.finance_tracker.repository;

import com.example.finance_tracker.entity.Income;
import com.example.finance_tracker.entity.User;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IncomeRepository extends JpaRepository<Income, Long> {
  Optional<Income> findByIdAndUserId(Long id, Long userId);
  List<Income> findByUser(User user);
}
