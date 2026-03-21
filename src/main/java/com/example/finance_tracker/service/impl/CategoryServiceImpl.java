package com.example.finance_tracker.service.impl;

import com.example.finance_tracker.dto.CategoryDTO;
import com.example.finance_tracker.model.ExpenseCategory;
import com.example.finance_tracker.model.User;
import com.example.finance_tracker.repository.ExpenseCategoryRepository;
import com.example.finance_tracker.service.CategoryService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CategoryServiceImpl implements CategoryService {

  private final ExpenseCategoryRepository repository;
  private final ModelMapper mapper;

  @Override
  @Transactional
  public CategoryDTO create(CategoryDTO dto, User user) {
    ExpenseCategory category;

    if(dto.getId() != null) {
      category = repository.findByIdAndUserId(dto.getId(), user.getId())
          .orElseThrow(() -> new IllegalArgumentException("Category id not found"));
      category.setName(dto.getName());
    } else {
      category = mapper.map(dto, ExpenseCategory.class);
      category.setUser(user);
    }

    repository.save(category);
    return mapper.map(category, CategoryDTO.class);
  }

  @Override
  public List<CategoryDTO> getAll(User user) {
    List<ExpenseCategory> categories = repository.findByUser(user);
    return categories.stream().map(category -> mapper.map(category, CategoryDTO.class)).toList();
  }

  @Override
  public CategoryDTO getOne(Long id, User user) {
    ExpenseCategory category = repository.findByIdAndUserId(id, user.getId())
        .orElseThrow(() -> new IllegalArgumentException("Category id not found"));
    return mapper.map(category, CategoryDTO.class);
  }

  @Override
  @Transactional
  public void delete(Long id, User user) {
    ExpenseCategory category = repository.findByIdAndUserId(id, user.getId())
        .orElseThrow(() -> new IllegalArgumentException("Category id not found"));
    repository.delete(category);
  }
}
