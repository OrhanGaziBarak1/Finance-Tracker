package com.example.finance_tracker.repository;

import com.example.finance_tracker.entity.IncomeCategory;
import com.example.finance_tracker.entity.User;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IncomeCategoryRepository extends JpaRepository<IncomeCategory, Long> {
  List<IncomeCategory> findByUser(User user);
  Optional<IncomeCategory> findByIdAndUserId(Long id, Long userId);
}
