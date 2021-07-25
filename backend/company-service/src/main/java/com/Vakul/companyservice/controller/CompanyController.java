package com.Vakul.companyservice.controller;
import java.time.LocalDate;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Vakul.companyservice.dto.CompanyDto;
import com.Vakul.companyservice.dto.IpoDto;
import com.Vakul.companyservice.dto.StockPriceDto;
import com.Vakul.companyservice.service.CompanyService;
import com.Vakul.companyservice.service.StockPriceService;

@RestController
@RequestMapping("company")
public class CompanyController {
    @Autowired
    private CompanyService companyService;

    @Autowired
    private StockPriceService stockPriceService;


    @PostMapping("/addCompany")
    public ResponseEntity<CompanyDto> createCompany(@RequestBody CompanyDto company) throws Exception {
        String companyName = company.getCompanyName();
        CompanyDto companyObj = companyService.findCompanyByName(companyName);
        if(companyObj!=null) {
            throw new Exception("Company with company-name "+companyName+" already exists!!!");
        }
        return new ResponseEntity<CompanyDto>(companyService.addCompany(company), HttpStatus.CREATED);
    }

    @GetMapping("/companies")
    public ResponseEntity<List<CompanyDto>> findAllCompanies(){
        return new ResponseEntity<List<CompanyDto>>(companyService.findAllCompany(), HttpStatus.FOUND);
    }

    @GetMapping(path = "/company/{id}")
    public ResponseEntity<CompanyDto> findCompanyById(@PathVariable("id") int companyId){
        return new ResponseEntity<CompanyDto>(companyService.findCompanyById(companyId), HttpStatus.FOUND);
    }

    @DeleteMapping(path = "/company/{id}")
    public ResponseEntity<String> deleteCompanyById(@PathVariable("id") int companyId){
        companyService.deleteCompanyById(companyId);
        return new ResponseEntity<String>("Deleted Successfull!!!",HttpStatus.OK);
    }

    @GetMapping("/company/stock-price")
    public List<StockPriceDto> findStockPriceByCompany(@RequestParam("companyId") int companyId,
                                                       @RequestParam("startDate") String startDate,
                                                       @RequestParam("endDate") String endDate){

        return stockPriceService.findStockPriceDetails(companyId, LocalDate.parse(startDate), LocalDate.parse(endDate));
    }

    @GetMapping("/company/{id}/ipo-details")
    public  List<IpoDto> findIpoDetailsByCompany(@PathVariable("id") int companyId){

        return companyService.findIpoDetailsByCompanyId(companyId);
    }

    @GetMapping("/companies/{pattern}")
    public List<CompanyDto> findCompaniesByPattern(@PathVariable("pattern") String pattern){
        return companyService.findCompanyByPattern(pattern);
    }
}
