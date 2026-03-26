package com.example.finance_tracker.repository;

import com.example.finance_tracker.entity.ExpenseCategory;
import com.example.finance_tracker.entity.User;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpenseCategoryRepository extends JpaRepository<ExpenseCategory, Long> {

  List<ExpenseCategory> findByUser(User user);
  Optional<ExpenseCategory> findByIdAndUserId(Long id, Long userId);

}
