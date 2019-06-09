package com.example.farming.entity;


import java.util.Date;

public class PlanManage {
    private Long id;

    private String name;

    private String planDate;

    private String sowDate;

    private Long landId;

    private String upMarketDate;

    private Double upHarvest;

    private String peakDate;

    private Double peakHarvest;

    private String downMarketDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getPlanDate() {
        return planDate;
    }

    public void setPlanDate(String planDate) {
        this.planDate = planDate;
    }

    public String getSowDate() {
        return sowDate;
    }

    public void setSowDate(String sowDate) {
        this.sowDate = sowDate;
    }

    public Long getLandId() {
        return landId;
    }

    public void setLandId(Long landId) {
        this.landId = landId;
    }

    public String getUpMarketDate() {
        return upMarketDate;
    }

    public void setUpMarketDate(String upMarketDate) {
        this.upMarketDate = upMarketDate;
    }

    public Double getUpHarvest() {
        return upHarvest;
    }

    public void setUpHarvest(Double upHarvest) {
        this.upHarvest = upHarvest;
    }

    public String getPeakDate() {
        return peakDate;
    }

    public void setPeakDate(String peakDate) {
        this.peakDate = peakDate;
    }

    public Double getPeakHarvest() {
        return peakHarvest;
    }

    public void setPeakHarvest(Double peakHarvest) {
        this.peakHarvest = peakHarvest;
    }

    public String getDownMarketDate() {
        return downMarketDate;
    }

    public void setDownMarketDate(String downMarketDate) {
        this.downMarketDate = downMarketDate;
    }
}