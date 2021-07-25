package com.Vakul.stockexchangeservice.service;

import java.lang.reflect.Type;
import java.util.List;

import com.Vakul.stockexchangeservice.dto.CompanyDto;
import com.Vakul.stockexchangeservice.dto.StockExchangeDto;
import com.Vakul.stockexchangeservice.model.Company;
import com.Vakul.stockexchangeservice.model.StockExchange;
import com.Vakul.stockexchangeservice.repository.StockExchangeRepo;


import org.modelmapper.TypeToken;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StockExchangeService {
    @Autowired
    private StockExchangeRepo stockExchangeRepo;

    @Autowired
    private ModelMapper modelMapper;

    public List<StockExchangeDto> findAllStockExchange(){
        Type listType = new TypeToken<List<StockExchangeDto>>(){}.getType();
        return modelMapper.map(stockExchangeRepo.findAll(), listType);
    }

    public StockExchangeDto addStockExchange(StockExchangeDto dto) throws Exception {
        if(stockExchangeRepo.findByStockExchangeName(dto.getStockExchangeName())!=null) {
            throw new Exception("Stock Exchange with name "+ dto.getStockExchangeName()+ " already exists!!!");
        }
        StockExchangeDto dto1 = modelMapper.map(stockExchangeRepo.save(modelMapper.map(dto, StockExchange.class)), StockExchangeDto.class);
        return dto1;
    }

    public List<CompanyDto> findAllCompanyInStockExchange(String stockExchangeName){
        StockExchange stockExchange = stockExchangeRepo.findByStockExchangeName(stockExchangeName);
        List<Company> companyList = stockExchange.getCompanyList();
        Type listType = new TypeToken<List<CompanyDto>>(){}.getType();
        return modelMapper.map(companyList, listType);
    }
}
