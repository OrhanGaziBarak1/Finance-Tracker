package com.example.finance_tracker.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Getter
@Setter
@Entity
@Table(name = "expenses")
public class Expense extends BaseEntity {

  @Column(name = "amount", nullable = false, precision = 19, scale = 2)
  private BigDecimal amount;

  @Column(name = "good",  nullable = false)
  private String good;

  @Column(name = "description")
  private String description;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id")
  private User user;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "category_id")
  @OnDelete(action = OnDeleteAction.SET_NULL)
  private ExpenseCategory category;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "source_id")
  @OnDelete(action = OnDeleteAction.SET_NULL)
  private ExpenseSource source;

  @Column(name = "expense_date", nullable = false)
  private LocalDate expenseDate;

}