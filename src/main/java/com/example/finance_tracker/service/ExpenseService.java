package com.example.finance_tracker.service;

import com.example.finance_tracker.dto.ExpenseDTO;
import com.example.finance_tracker.model.User;
import java.util.List;

public interface ExpenseService {
  ExpenseDTO create(ExpenseDTO ExpenseDTO, User user);
  List<ExpenseDTO> getAll(User user);
  ExpenseDTO getOne(Long id, User user);
  void delete(Long id, User user);
}
