package com.example.finance_tracker.service;

import com.example.finance_tracker.dto.ExpenseSourceDTO;
import com.example.finance_tracker.entity.User;
import java.util.List;

public interface ExpenseSourceService {
  ExpenseSourceDTO create(ExpenseSourceDTO dto, User user);
  List<ExpenseSourceDTO> getAll(User user);
  ExpenseSourceDTO getOne(Long id, User user);
  void delete(Long id, User user);
}
