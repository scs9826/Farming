package com.example.farming.entity;

import java.util.Date;

public class FarmworkRecord {
    private Long id;

    private String date;

    private Long landId;

    private Integer fee;

    private String farmwork;

    private String ingredientName;

    private Double ingredientNum;

    private String seedName;

    private Double seedNum;

    private Double cost;

    private Double profits;

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

    public Long getLandId() {
        return landId;
    }

    public void setLandId(Long landId) {
        this.landId = landId;
    }

    public Integer getFee() {
        return fee;
    }

    public void setFee(Integer fee) {
        this.fee = fee;
    }

    public String getFarmwork() {
        return farmwork;
    }

    public void setFarmwork(String farmwork) {
        this.farmwork = farmwork;
    }

    public String getIngredientName() {
        return ingredientName;
    }

    public void setIngredientName(String ingredientName) {
        this.ingredientName = ingredientName;
    }

    public Double getIngredientNum() {
        return ingredientNum;
    }

    public void setIngredientNum(Double ingredientNum) {
        this.ingredientNum = ingredientNum;
    }

    public String getSeedName() {
        return seedName;
    }

    public void setSeedName(String seedName) {
        this.seedName = seedName;
    }

    public Double getSeedNum() {
        return seedNum;
    }

    public void setSeedNum(Double seedNum) {
        this.seedNum = seedNum;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public Double getProfits() {
        return profits;
    }

    public void setProfits(Double profits) {
        this.profits = profits;
    }
}