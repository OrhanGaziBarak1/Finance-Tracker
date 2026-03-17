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

@Getter
@Setter
@Entity
@Table(name = "incomes")
public class Income extends BaseEntity {

  @Column(name = "amount", precision = 19, scale = 2, nullable = false)
  private BigDecimal amount;

  @Column(name = "name")
  private String name;

  @Column(name = "income_date")
  private LocalDate incomeDate;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id")
  private User user;

}