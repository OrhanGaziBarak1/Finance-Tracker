package com.example.finance_tracker.service.impl;

import com.example.finance_tracker.dto.IncomeDTO;
import com.example.finance_tracker.entity.Income;
import com.example.finance_tracker.entity.User;
import com.example.finance_tracker.exception.NotFoundException;
import com.example.finance_tracker.repository.IncomeCategoryRepository;
import com.example.finance_tracker.repository.IncomeRepository;
import com.example.finance_tracker.service.IncomeService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class IncomeServiceImpl implements IncomeService {

  private final IncomeRepository repository;
  private final IncomeCategoryRepository incomeCategoryRepository;
  private final ModelMapper mapper;

  @Override
  @Transactional
  public IncomeDTO create(IncomeDTO dto, User user) {
    Income income;

    if (dto.getId() != null) {
      income = repository.findByIdAndUserId(dto.getId(), user.getId())
          .orElseThrow(() -> new NotFoundException("Income with id " + dto.getId() + " not found"));
      income.setAmount(dto.getAmount());
      income.setName(dto.getName());
      income.setIncomeDate(dto.getIncomeDate());
    } else {
      income = mapper.map(dto, Income.class);
      income.setUser(user);
    }

    income.setIncomeCategory(incomeCategoryRepository.getReferenceById(dto.getIncomeCategoryId()));

    repository.save(income);
    return mapper.map(income, IncomeDTO.class);
  }

  @Override
  public List<IncomeDTO> getAll(User user) {
    List<Income> incomes = repository.findByUser(user);
    return incomes.stream().map((element) -> mapper.map(element, IncomeDTO.class)).toList();
  }

  @Override
  public IncomeDTO getOne(Long id, User user) {
    Income income = repository.findById(id)
        .orElseThrow(() -> new NotFoundException("Income with id " + id + " not found"));
    return mapper.map(income, IncomeDTO.class);
  }

  @Override
  @Transactional
  public void delete(Long id, User user) {
    Income income = repository.findByIdAndUserId(id, user.getId())
        .orElseThrow(() -> new NotFoundException("Income with id " + id + " not found"));
    repository.delete(income);
  }
}
