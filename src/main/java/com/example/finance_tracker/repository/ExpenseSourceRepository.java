package com.example.finance_tracker.repository;

import com.example.finance_tracker.model.ExpenseSource;
import com.example.finance_tracker.model.User;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpenseSourceRepository extends JpaRepository<ExpenseSource, Long> {

  Optional<ExpenseSource> findByIdAndUserId(Long id, Long userId);
  List<ExpenseSource> findByUser(User user);

}
