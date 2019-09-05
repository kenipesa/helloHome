package com.kenipesa.helloHome.models;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpensesRepository extends JpaRepository<Expenses, Long> {
  public Expenses findByBuyerId(Long applicationUserId);
}
