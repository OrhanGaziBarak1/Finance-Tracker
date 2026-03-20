package com.example.finance_tracker.repository;

import com.example.finance_tracker.model.Expense;
import com.example.finance_tracker.model.User;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {

  Optional<Expense> findByIdAndUserId(Long id, Long userId);
  List<Expense> findByUser(User user);

}
