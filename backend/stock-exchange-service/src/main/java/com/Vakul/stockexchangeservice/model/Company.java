package com.Vakul.stockexchangeservice.model;


import java.util.List;
import java.util.ArrayList;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;


@Entity
public class Company {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    @Column(name = "company_name")
    private String companyName;

    @Column(name = "turn_over")
    private int turnOver;

//	@Column(name = "board_of_directors")
//	private List<String> boradOfDirectors;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
            name="Company_StockExchange",
            joinColumns = {@JoinColumn(name="company_id")},
            inverseJoinColumns = {@JoinColumn(name="stock_exchange_id")}
    )
    private List<StockExchange> stockExchangeList = new ArrayList<StockExchange>();


    private int sectorId;

    private String description;


    public Company() {
        super();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public int getTurnOver() {
        return turnOver;
    }

    public void setTurnOver(int turnOver) {
        this.turnOver = turnOver;
    }

    public List<StockExchange> getStockExchangeList() {
        return stockExchangeList;
    }

    public void addStockExchangeList(StockExchange stockExchange) {
        this.stockExchangeList.add(stockExchange);
    }


    public int getSectorId() {
        return sectorId;
    }

    public void setSectorId(int sectorId) {
        this.sectorId = sectorId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }



    @Override
    public String toString() {
        return "Company [id=" + id + ", companyName=" + companyName + ", turnOver=" + turnOver + ", sectorId="
                + sectorId + ", description=" + description + "]";
    }




}