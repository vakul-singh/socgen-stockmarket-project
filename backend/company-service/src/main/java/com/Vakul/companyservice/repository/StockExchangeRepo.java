package com.Vakul.companyservice.repository;

import com.Vakul.companyservice.model.StockExchange;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface StockExchangeRepo extends JpaRepository<StockExchange, Integer>{

    public StockExchange findByStockExchangeName(String name);
}