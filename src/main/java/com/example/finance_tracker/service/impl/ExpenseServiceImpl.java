package com.example.finance_tracker.service.impl;

import com.example.finance_tracker.dto.ExpenseDTO;
import com.example.finance_tracker.model.Expense;
import com.example.finance_tracker.model.User;
import com.example.finance_tracker.repository.CategoryRepository;
import com.example.finance_tracker.repository.ExpenseRepository;
import com.example.finance_tracker.repository.ExpenseSourceRepository;
import com.example.finance_tracker.service.ExpenseService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ExpenseServiceImpl implements ExpenseService {
  
  private final ExpenseRepository repository;
  private final CategoryRepository categoryRepository;
  private final ExpenseSourceRepository expenseSourceRepository;
  private final ModelMapper mapper;

  @Override
  @Transactional
  public ExpenseDTO create(ExpenseDTO dto, User user) {
    Expense expense;

    if(dto.getId() != null) {
      expense = repository.findByIdAndUserId(dto.getId(), user.getId())
          .orElseThrow(() -> new IllegalArgumentException("expense id not found"));
      expense.setAmount(dto.getAmount());
      expense.setGood(dto.getGood());
      expense.setDescription(dto.getDescription());
      expense.setExpenseDate(dto.getExpenseDate());
    } else {
      expense = mapper.map(dto, Expense.class);
      expense.setUser(user);
    }
    expense.setCategory(categoryRepository.getReferenceById(dto.getCategoryId()));
    expense.setSource(expenseSourceRepository.getReferenceById(dto.getSourceId()));

    repository.save(expense);
    return mapper.map(expense, ExpenseDTO.class);
  }

  @Override
  public List<ExpenseDTO> getAll(User user) {
    List<Expense> expenses = repository.findByUser(user);
    return expenses.stream().map(Expense -> mapper.map(Expense, ExpenseDTO.class)).toList();
  }

  @Override
  public ExpenseDTO getOne(Long id, User user) {
    Expense expense = repository.findByIdAndUserId(id, user.getId())
        .orElseThrow(() -> new IllegalArgumentException("expense id not found"));
    return mapper.map(expense, ExpenseDTO.class);
  }

  @Override
  @Transactional
  public void delete(Long id, User user) {
    Expense expense = repository.findByIdAndUserId(id, user.getId())
        .orElseThrow(() -> new IllegalArgumentException("expense id not found"));
    repository.delete(expense);
  }
}
