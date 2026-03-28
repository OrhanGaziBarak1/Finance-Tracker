package com.example.finance_tracker.service.impl;

import com.example.finance_tracker.dto.CategoryDTO;
import com.example.finance_tracker.entity.IncomeCategory;
import com.example.finance_tracker.entity.User;
import com.example.finance_tracker.exception.NotFoundException;
import com.example.finance_tracker.repository.IncomeCategoryRepository;
import com.example.finance_tracker.service.IncomeCategoryService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class IncomeCategoryServiceImpl implements IncomeCategoryService {

  private final IncomeCategoryRepository repository;
  private final ModelMapper mapper;

  @Override
  @Transactional
  public CategoryDTO create(CategoryDTO dto, User user) {
    IncomeCategory incomeCategory;

    if(dto.getId() != null) {
      incomeCategory = repository.findByIdAndUserId(dto.getId(), user.getId())
          .orElseThrow(() -> new NotFoundException("Income category with id " + dto.getId() + " not found"));
      incomeCategory.setName(dto.getName());
    } else {
      incomeCategory = mapper.map(dto, IncomeCategory.class);
      incomeCategory.setUser(user);
    }

    repository.save(incomeCategory);
    return mapper.map(incomeCategory, CategoryDTO.class);
  }

  @Override
  public List<CategoryDTO> getAll(User user) {
    List<IncomeCategory> incomeCategories = repository.findByUser(user);
    return incomeCategories.stream().map(category -> mapper.map(category, CategoryDTO.class)).toList();
  }

  @Override
  public CategoryDTO getOne(Long id, User user) {
    IncomeCategory incomeCategory = repository.findByIdAndUserId(id, user.getId())
        .orElseThrow(() -> new NotFoundException("Income category with id " + id + " not found"));
    return mapper.map(incomeCategory, CategoryDTO.class);
  }

  @Override
  @Transactional
  public void delete(Long id, User user) {
    IncomeCategory incomeCategory = repository.findByIdAndUserId(id, user.getId())
        .orElseThrow(() -> new NotFoundException("Income category with id " + id + " not found"));
    repository.delete(incomeCategory);
  }
}
