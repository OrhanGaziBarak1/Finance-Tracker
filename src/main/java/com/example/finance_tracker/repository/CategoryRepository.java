package com.example.finance_tracker.repository;

import com.example.finance_tracker.model.Category;
import com.example.finance_tracker.model.User;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

  List<Category> findByUser(User user);
  Optional<Category> findByIdAndUserId(Long id, Long userId);

}
