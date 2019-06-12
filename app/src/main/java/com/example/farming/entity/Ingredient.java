/*刘云杰*/
package com.example.farming.entity;

import java.util.Date;

public class Ingredient {
    private Long id;

    private String date;

    private String name;

    private Integer fee;

    private Double lossRatio;

    private Double chicken;

    private Double horse;

    private Double ox;

    private Double cake;

    private Double husk;

    private Double shell;

    private Double straw;

    private Double sawdust;

    private Double water;

    private Double leaf;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getFee() {
        return fee;
    }

    public void setFee(Integer fee) {
        this.fee = fee;
    }

    public Double getLossRatio() {
        return lossRatio;
    }

    public void setLossRatio(Double lossRatio) {
        this.lossRatio = lossRatio;
    }

    public Double getChicken() {
        return chicken;
    }

    public void setChicken(Double chicken) {
        this.chicken = chicken;
    }

    public Double getHorse() {
        return horse;
    }

    public void setHorse(Double horse) {
        this.horse = horse;
    }

    public Double getOx() {
        return ox;
    }

    public void setOx(Double ox) {
        this.ox = ox;
    }

    public Double getCake() {
        return cake;
    }

    public void setCake(Double cake) {
        this.cake = cake;
    }

    public Double getHusk() {
        return husk;
    }

    public void setHusk(Double husk) {
        this.husk = husk;
    }

    public Double getShell() {
        return shell;
    }

    public void setShell(Double shell) {
        this.shell = shell;
    }

    public Double getStraw() {
        return straw;
    }

    public void setStraw(Double straw) {
        this.straw = straw;
    }

    public Double getSawdust() {
        return sawdust;
    }

    public void setSawdust(Double sawdust) {
        this.sawdust = sawdust;
    }

    public Double getWater() {
        return water;
    }

    public void setWater(Double water) {
        this.water = water;
    }

    public Double getLeaf() {
        return leaf;
    }

    public void setLeaf(Double leaf) {
        this.leaf = leaf;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}