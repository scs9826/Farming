package com.example.farming.entity;


import java.util.Date;

public class HarvestManage {
    private Long id;

    private String date;

    private String seedName;

    private Double harvestNum;

    private Double price;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSeedName() {
        return seedName;
    }

    public void setSeedName(String seedName) {
        this.seedName = seedName == null ? null : seedName.trim();
    }

    public Double getHarvestNum() {
        return harvestNum;
    }

    public void setHarvestNum(Double harvestNum) {
        this.harvestNum = harvestNum;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}