package com.example.finance_tracker.service.impl;

import com.example.finance_tracker.dto.ExpenseSourceDTO;
import com.example.finance_tracker.entity.Expense;
import com.example.finance_tracker.entity.ExpenseSource;
import com.example.finance_tracker.entity.User;
import com.example.finance_tracker.exception.NotFoundException;
import com.example.finance_tracker.repository.ExpenseSourceRepository;
import com.example.finance_tracker.service.ExpenseSourceService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ExpenseSourceServiceImpl implements ExpenseSourceService {

  private final ExpenseSourceRepository repository;
  private final ModelMapper mapper;

  @Override
  @Transactional
  public ExpenseSourceDTO create(ExpenseSourceDTO dto, User user) {
    ExpenseSource source;

    if(dto.getId() != null) {
      source = repository.findByIdAndUserId(dto.getId(), user.getId())
          .orElseThrow(() -> new NotFoundException("error.business.notFoundWithId", dto.getId(),
              ExpenseSource.class.getSimpleName()));
      source.setName(dto.getName());
    } else {
      source = mapper.map(dto, ExpenseSource.class);
      source.setUser(user);
    }

    repository.save(source);
    return mapper.map(source, ExpenseSourceDTO.class);
  }

  @Override
  public List<ExpenseSourceDTO> getAll(User user) {
    List<ExpenseSource> sources = repository.findByUser(user);
    return sources.stream().map(source -> mapper.map(source,  ExpenseSourceDTO.class)).toList();
  }

  @Override
  public ExpenseSourceDTO getOne(Long id, User user) {
    ExpenseSource source = repository.findByIdAndUserId(id, user.getId())
        .orElseThrow(() -> new NotFoundException("error.business.notFoundWithId", id,
            ExpenseSource.class.getSimpleName()));
    return mapper.map(source,  ExpenseSourceDTO.class);
  }

  @Override
  @Transactional
  public void delete(Long id, User user) {
    ExpenseSource source = repository.findByIdAndUserId(id, user.getId())
        .orElseThrow(() -> new NotFoundException("error.business.notFoundWithId", id,
            ExpenseSource.class.getSimpleName()));
    repository.delete(source);
  }
}
