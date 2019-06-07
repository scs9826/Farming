package com.example.farming.entity;


import java.util.Date;

public class PlanManage {
    private Long id;

    private String name;

    private Date planDate;

    private Date sowDate;

    private Long landId;

    private Date upMarketDate;

    private Double upHarvest;

    private Date peakDate;

    private Double peakHarvest;

    private Date downMarketDate;

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

    public Date getPlanDate() {
        return planDate;
    }

    public void setPlanDate(Date planDate) {
        this.planDate = planDate;
    }

    public Date getSowDate() {
        return sowDate;
    }

    public void setSowDate(Date sowDate) {
        this.sowDate = sowDate;
    }

    public Long getLandId() {
        return landId;
    }

    public void setLandId(Long landId) {
        this.landId = landId;
    }

    public Date getUpMarketDate() {
        return upMarketDate;
    }

    public void setUpMarketDate(Date upMarketDate) {
        this.upMarketDate = upMarketDate;
    }

    public Double getUpHarvest() {
        return upHarvest;
    }

    public void setUpHarvest(Double upHarvest) {
        this.upHarvest = upHarvest;
    }

    public Date getPeakDate() {
        return peakDate;
    }

    public void setPeakDate(Date peakDate) {
        this.peakDate = peakDate;
    }

    public Double getPeakHarvest() {
        return peakHarvest;
    }

    public void setPeakHarvest(Double peakHarvest) {
        this.peakHarvest = peakHarvest;
    }

    public Date getDownMarketDate() {
        return downMarketDate;
    }

    public void setDownMarketDate(Date downMarketDate) {
        this.downMarketDate = downMarketDate;
    }
}