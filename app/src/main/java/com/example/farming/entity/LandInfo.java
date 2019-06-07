package com.example.farming.entity;


public class LandInfo {
    private Long id;

    private Double square;

    private Long uid;

    private String region;

    private Double regionSquare;

    private String tag;

    private Double tagSquare;

    private Integer block;

    private String place;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getSquare() {
        return square;
    }

    public void setSquare(Double square) {
        this.square = square;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region == null ? null : region.trim();
    }

    public Double getRegionSquare() {
        return regionSquare;
    }

    public void setRegionSquare(Double regionSquare) {
        this.regionSquare = regionSquare;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag == null ? null : tag.trim();
    }

    public Double getTagSquare() {
        return tagSquare;
    }

    public void setTagSquare(Double tagSquare) {
        this.tagSquare = tagSquare;
    }

    public Integer getBlock() {
        return block;
    }

    public void setBlock(Integer block) {
        this.block = block;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place == null ? null : place.trim();
    }
}