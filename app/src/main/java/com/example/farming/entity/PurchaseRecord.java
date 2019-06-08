package com.example.farming.entity;

import java.util.Date;

public class PurchaseRecord {
    private Long id;
    private String date;

    private String name;

    private Integer num;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "PurchaseRecord{" +
                "id=" + id +
                ", date=" + date +
                ", name='" + name + '\'' +
                ", num=" + num +
                ", price=" + price +
                '}';
    }
}