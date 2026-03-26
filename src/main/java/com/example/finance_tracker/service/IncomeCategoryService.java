package com.example.finance_tracker.service;

import com.example.finance_tracker.dto.CategoryDTO;
import com.example.finance_tracker.entity.User;
import java.util.List;

public interface IncomeCategoryService {
  CategoryDTO create(CategoryDTO dto, User user);
  List<CategoryDTO> getAll(User user);
  CategoryDTO getOne(Long id, User user);
  void delete(Long id, User user);
}
