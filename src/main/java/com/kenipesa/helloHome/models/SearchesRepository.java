package com.kenipesa.helloHome.models;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SearchesRepository extends JpaRepository<Searches, Long> {
  public Searches findByBuyerId(Long applicationUserId);
  public Searches findByCityAndStateAndBuyerId(String city, String state, Long buyerId);
}
