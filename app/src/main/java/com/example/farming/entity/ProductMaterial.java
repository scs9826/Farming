package com.example.farming.entity;

import java.util.Map;

public class ProductMaterial {
    private Map<String, Integer> seed;
    private Map<String, Integer> ingredient;

    public Map<String, Integer> getIngredient() {
        return ingredient;
    }

    public void setIngredient(Map<String, Integer> ingredient) {
        this.ingredient = ingredient;
    }

    public Map<String, Integer> getSeed() {
        return seed;
    }

    public void setSeed(Map<String, Integer> seed) {
        this.seed = seed;
    }
}
