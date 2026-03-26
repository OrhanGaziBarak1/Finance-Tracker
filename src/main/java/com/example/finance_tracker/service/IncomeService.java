package com.example.finance_tracker.service;

import com.example.finance_tracker.dto.IncomeDTO;
import com.example.finance_tracker.entity.User;
import java.util.List;

public interface IncomeService {
  IncomeDTO create(IncomeDTO dto, User user);
  List<IncomeDTO> getAll(User user);
  IncomeDTO getOne(Long id, User user);
  void delete(Long id, User user);
}
