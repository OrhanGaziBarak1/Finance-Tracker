package com.example.finance_tracker.repository;

import com.example.finance_tracker.model.ExpenseSource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpenseSourceRepository extends JpaRepository<ExpenseSource, Long> {

}
