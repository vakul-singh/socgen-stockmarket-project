package com.Vakul.stockexchangeservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Vakul.stockexchangeservice.model.StockExchange;

@Repository
public interface StockExchangeRepo extends JpaRepository<StockExchange, Integer>{

    public StockExchange findByStockExchangeName(String stockExchangeName);
}
