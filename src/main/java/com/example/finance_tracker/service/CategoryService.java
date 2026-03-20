package com.example.finance_tracker.service;

import com.example.finance_tracker.dto.CategoryDTO;
import com.example.finance_tracker.model.User;
import java.util.List;

public interface CategoryService {
  CategoryDTO create(CategoryDTO categoryDTO, User user);
  List<CategoryDTO> getAll(User user);
  CategoryDTO getOne(Long id, User user);
  void delete(Long id, User user);
}
